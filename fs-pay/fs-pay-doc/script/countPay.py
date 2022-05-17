#encoding:utf8
import MySQLdb
import datetime
import codecs
import sys
import time

today = datetime.date.today() 
yesterday = today - datetime.timedelta(days=1) 

today_time = int(time.mktime(today.timetuple())) * 1000
yesterday_time = int(time.mktime(yesterday.timetuple())) * 1000

sql_charge = 'SELECT SUM(amount) FROM user_charge WHERE create_time > %d AND create_time <= %d and status = 1'%(yesterday_time, today_time);
sql_getmoney = 'SELECT SUM(amount) FROM user_getmoney WHERE create_time > %d AND create_time <= %d'%(yesterday_time, today_time);
sql_luckymoney = 'SELECT COUNT(*) FROM user_transfer WHERE trans_type = 20006  AND create_time > %d AND create_time <= %d and status <= 2'%(yesterday_time, today_time);
sql_get_luckymoney = 'SELECT COUNT(*) FROM user_transfer WHERE trans_type = 20004 AND create_time > %d AND create_time <= %d and status <= 2'%(yesterday_time, today_time);
sql_bind_card = 'SELECT COUNT(*) FROM user_card_info WHERE create_time > %d AND create_time <= %d'%(yesterday_time, today_time);

sql_bind_card_times_all = 'SELECT COUNT(1) FROM user_card_info';
sql_bind_card_user_all = 'SELECT COUNT(1) FROM (SELECT enterprise_account,fs_user_id FROM user_card_info GROUP BY enterprise_account,fs_user_id) m';
sql_money_send_amount_all = 'SELECT SUM(amount) FROM pay_business.user_transfer WHERE trans_type = 20006  AND STATUS <3';
sql_money_send_times_all = 'SELECT COUNT(1) FROM pay_business.user_transfer WHERE trans_type = 20006  AND STATUS <3';
sql_money_send_user_all = 'SELECT COUNT(1) FROM (SELECT COUNT(1) FROM pay_business.user_transfer WHERE trans_type = 20006  AND STATUS <3 GROUP BY from_user_id,from_enterprise_account ) a';

db = MySQLdb.connect('172.17.22.253','mha','mha','pay_business',charset='utf8')
#db = MySQLdb.connect('172.31.105.105','fte2','pp00--[[','pay_business',charset='utf8')
cursor = db.cursor()

cursor.execute(sql_charge)
row =cursor.fetchone() 
charge = row[0]

cursor.execute(sql_getmoney)
row =cursor.fetchone() 
getmoney = row[0]

cursor.execute(sql_luckymoney)
row =cursor.fetchone() 
luckymoney = row[0]

cursor.execute(sql_get_luckymoney)
row =cursor.fetchone() 
get_luckymoney = row[0]

cursor.execute(sql_bind_card)
row =cursor.fetchone() 
bind_card = row[0]

#count all data
cursor.execute(sql_bind_card_times_all)
row =cursor.fetchone() 
bind_card_times_all = row[0]

cursor.execute(sql_bind_card_user_all)
row =cursor.fetchone() 
bind_card_user_all = row[0]

cursor.execute(sql_money_send_amount_all)
row =cursor.fetchone() 
money_send_amount_all = row[0]

cursor.execute(sql_money_send_times_all)
row =cursor.fetchone() 
money_send_times_all = row[0]

cursor.execute(sql_money_send_user_all)
row =cursor.fetchone() 
money_send_user_all = row[0]


db_insert = MySQLdb.connect('172.17.22.5','open','adWEW8237:p!','open_operation_stat',charset='utf8')
#db_insert = MySQLdb.connect('172.31.105.105','fte2','pp00--[[','open_operation_stat',charset='utf8')
cursor_insert = db_insert.cursor()

sql_start = 'insert into app_pay_operation (create_date, record_date, red_envelpoe_count, bind_card_count, get_red_envelope, charge_amount, fetch_amount,total_red_envelope_usage, total_red_envelope_amount, total_bind_card_usage, total_bind_card_count, total_sent_red_envelope) values ("%s","%s","%d","%d","%d","%s","%s","%d","%s","%d","%d","%d")'
cursor_insert.execute(sql_start%(today, yesterday, luckymoney, bind_card, get_luckymoney, charge, getmoney, money_send_user_all, money_send_amount_all, bind_card_user_all, bind_card_times_all, money_send_times_all))

db_insert.commit()

    
db.close()
db_insert.close()
print 'done'
