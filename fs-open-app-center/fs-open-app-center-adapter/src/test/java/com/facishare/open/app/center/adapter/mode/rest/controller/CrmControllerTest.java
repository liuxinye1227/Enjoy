package com.facishare.open.app.center.adapter.mode.rest.controller;

import com.facishare.open.app.center.adapter.model.proto.AddCrmAppAdmins;
import com.facishare.open.app.center.adapter.model.proto.ProtobufResult;
import com.facishare.open.app.center.adapter.model.proto.QueryCrmAppAdmins;
import com.facishare.open.app.center.adapter.model.proto.QueryQuota;
import com.facishare.open.app.center.adapter.model.proto.RemoveCrmAppAdmins;
import com.facishare.open.app.center.adapter.model.proto.SaveUsers;
import com.facishare.open.app.center.adapter.model.rest.controller.CrmController;
import com.facishare.open.app.center.api.model.AppViewDO;
import com.facishare.open.app.center.api.model.enums.AppCenterCodeEnum;
import com.facishare.open.app.center.api.model.enums.AppComponentTypeEnum;
import com.facishare.open.app.center.api.service.OpenAppAdminService;
import com.facishare.open.app.center.api.service.OpenFsUserAppViewService;
import com.facishare.open.app.center.api.service.QueryAppAdminService;
import com.facishare.open.app.center.api.service.externals.AppViewBizService;
import com.facishare.open.app.pay.api.service.QuotaService;
import com.facishare.open.common.model.FsUserVO;
import com.facishare.open.common.result.BaseResult;
import com.facishare.open.common.result.exception.BizException;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyCollectionOf;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

/**
 * Created by xialf on 7/7/16.
 *
 * @author xialf
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/unit-test.xml"})
@Transactional
public class CrmControllerTest {

    @Resource
    private CrmController crmController;

    @Resource
    private QuotaService quotaService;

    @Resource
    private OpenFsUserAppViewService openFsUserAppViewService;

    @Resource
    private OpenAppAdminService openAppAdminService;

    @Resource
    private QueryAppAdminService queryAppAdminService;

    @Resource
    private AppViewBizService appViewBizService;

    @Before
    public void setUp() {
        reset(quotaService, openFsUserAppViewService, openAppAdminService, queryAppAdminService, appViewBizService);
        when(quotaService.queryQuota(anyString(), anyString())).thenReturn(new BaseResult<>(999));
        when(openFsUserAppViewService.loadAppViewByType(any(FsUserVO.class), anyString(), any(AppComponentTypeEnum.class)))
                .thenReturn(new com.facishare.open.app.center.api.result.BaseResult<>(new AppViewDO()));
        when(openFsUserAppViewService.saveFsUserAppViewList(any(FsUserVO.class), anyString(), any(AppComponentTypeEnum.class), any(AppViewDO.class)))
                .thenReturn(new com.facishare.open.app.center.api.result.BaseResult<Void>());

        when(appViewBizService.addEmployeeView(anyString(), anyString(), anyListOf(Integer.class)))
                .thenReturn(new BaseResult<>());

        final HashBasedTable<String, String, Set<Integer>> appAdmins = HashBasedTable.create();
        when(openAppAdminService.addAppAdminIds(any(FsUserVO.class), anyString(), anyCollectionOf(Integer.class)))
                .thenAnswer(invocationOnMock -> {
                    final FsUserVO fsUser = invocationOnMock.getArgumentAt(0, FsUserVO.class);
                    final String appId = invocationOnMock.getArgumentAt(1, String.class);
                    @SuppressWarnings("unchecked")
                    final List<Integer> admins = invocationOnMock.getArgumentAt(2, List.class);
                    if (appAdmins.contains(fsUser.getEnterpriseAccount(), appId)) {
                        appAdmins.get(fsUser.getEnterpriseAccount(), appId).addAll(admins);
                    } else {
                        appAdmins.put(fsUser.getEnterpriseAccount(), appId, Sets.newHashSet(admins));
                    }
                    return new com.facishare.open.app.center.api.result.BaseResult<Void>();
                });
        when(openAppAdminService.removeAppAdminIds(any(FsUserVO.class), anyString(), anyCollectionOf(Integer.class)))
                .thenAnswer(invocationOnMock -> {
                    final FsUserVO fsUser = invocationOnMock.getArgumentAt(0, FsUserVO.class);
                    final String appId = invocationOnMock.getArgumentAt(1, String.class);
                    @SuppressWarnings("unchecked")
                    final List<Integer> admins = invocationOnMock.getArgumentAt(2, List.class);
                    if (appAdmins.contains(fsUser.getEnterpriseAccount(), appId)) {
                        appAdmins.get(fsUser.getEnterpriseAccount(), appId).removeAll(admins);
                    }
                    return new com.facishare.open.app.center.api.result.BaseResult<Void>();
                });
        when(queryAppAdminService.findAppAdminListByAppId(anyString(), anyString()))
                .thenAnswer(invocationOnMock -> {
                    final String fsEa = invocationOnMock.getArgumentAt(0, String.class);
                    final String appId = invocationOnMock.getArgumentAt(1, String.class);
                    if (appAdmins.contains(fsEa, appId)) {
                        final List<FsUserVO> fsUserIds = appAdmins.get(fsEa, appId).stream().map(userId -> new FsUserVO(fsEa, userId)).collect(Collectors.toList());
                        return new BaseResult<>(fsUserIds);
                    }
                    return new BaseResult<>(Lists.<FsUserVO>newArrayList());
                });
    }

    @Test
    public void saveUsers_Success() {
        final SaveUsers.Arg arg = new SaveUsers.Arg();
        arg.setFsEa("1064");
        arg.setUserIds(Lists.newArrayList(1, 2, 3));
        final SaveUsers.Result result = crmController.saveUsers(arg);

        Assert.assertEquals(ProtobufResult.OK_CODE, (int) result.getCode());
//        verify(openFsUserAppViewService, only()).loadAppViewByType(any(FsUserVO.class), anyString(), any(AppComponentTypeEnum.class));
//        verify(openFsUserAppViewService, only()).saveFsUserAppViewList(any(FsUserVO.class), anyString(), any(AppComponentTypeEnum.class), any(AppViewDO.class));
    }

    @Test
    public void queryAppAdmin_Success() {
        final QueryCrmAppAdmins.Arg arg = new QueryCrmAppAdmins.Arg();
        arg.setFsEa(FS_EA);
        arg.setOperatorId(OPERATOR_ID);
        final QueryCrmAppAdmins.Result result = crmController.queryAppAdmins(arg);
        Assert.assertEquals(ProtobufResult.OK_CODE, (int) result.getCode());
        Assert.assertTrue(result.getAppAdmins().isEmpty());
    }



    private static final int OPERATOR_ID = 2969;
    private static final String FS_EA = "fstest";

    @Test
    public void queryAppAdmin_AddOne_QueryOne() {
        final AddCrmAppAdmins.Arg addArg = new AddCrmAppAdmins.Arg();
        addArg.setFsEa(FS_EA);
        addArg.setOperatorId(OPERATOR_ID);
        addArg.setAppAdmins(Lists.newArrayList(1, 2, 3));
        final AddCrmAppAdmins.Result addResult = crmController.addAppAdmins(addArg);
        Assert.assertEquals(ProtobufResult.OK_CODE, (int) addResult.getCode());

        final QueryCrmAppAdmins.Arg arg = new QueryCrmAppAdmins.Arg();
        arg.setFsEa(FS_EA);
        arg.setOperatorId(OPERATOR_ID);
        final QueryCrmAppAdmins.Result result = crmController.queryAppAdmins(arg);
        Assert.assertEquals(ProtobufResult.OK_CODE, (int) result.getCode());
        Assert.assertEquals(Lists.newArrayList(1, 2, 3), result.getAppAdmins());
    }

    @Test
    public void queryAppAdmin_AddThree_RemoveOne_QueryOne() {
        final AddCrmAppAdmins.Arg addArg = new AddCrmAppAdmins.Arg();
        addArg.setFsEa(FS_EA);
        addArg.setOperatorId(OPERATOR_ID);
        addArg.setAppAdmins(Lists.newArrayList(1, 2, 3));
        final AddCrmAppAdmins.Result addResult = crmController.addAppAdmins(addArg);
        Assert.assertEquals(addResult.getMsg(), ProtobufResult.OK_CODE, (int) addResult.getCode());

        final RemoveCrmAppAdmins.Arg removeArg = new RemoveCrmAppAdmins.Arg();
        removeArg.setFsEa(FS_EA);
        removeArg.setOperatorId(OPERATOR_ID);
        removeArg.setAppAdmins(Lists.newArrayList(1, 3));
        final RemoveCrmAppAdmins.Result removeResult = crmController.removeAppAdmins(removeArg);
        Assert.assertEquals(ProtobufResult.OK_CODE, (int) removeResult.getCode());

        final QueryCrmAppAdmins.Arg arg = new QueryCrmAppAdmins.Arg();
        arg.setFsEa(FS_EA);
        arg.setOperatorId(OPERATOR_ID);
        final QueryCrmAppAdmins.Result result = crmController.queryAppAdmins(arg);
        Assert.assertEquals(result.getMsg(), ProtobufResult.OK_CODE, (int) result.getCode());
        Assert.assertEquals(Lists.newArrayList(2), result.getAppAdmins());
    }

    @Test
    public void queryQuota_Success() {
        final QueryQuota.Arg queryQuotaArg = new QueryQuota.Arg();
        queryQuotaArg.setFsEa("1064");

        final QueryQuota.Result arg = crmController.queryQuota(queryQuotaArg);
        Assert.assertEquals(ProtobufResult.OK_CODE, (int) arg.getCode());
        Assert.assertEquals(999, (int) arg.getQuota());
    }

    @Test
    public void queryQuota_BizException() {
        reset(quotaService);
        when(quotaService.queryQuota(anyString(), anyString())).thenReturn(new BaseResult<>(new BizException(AppCenterCodeEnum.QUOTA_INSUFFICIENT)));
        final QueryQuota.Arg queryQuotaArg = new QueryQuota.Arg();
        queryQuotaArg.setFsEa("1064");

        final QueryQuota.Result result = crmController.queryQuota(queryQuotaArg);
        Assert.assertEquals(AppCenterCodeEnum.QUOTA_INSUFFICIENT.getErrCode(), (int) result.getCode());
        Assert.assertNull(result.getQuota());
    }

    @Test
    public void queryQuota_IllegalArgument() {
        final QueryQuota.Arg queryQuotaArg = new QueryQuota.Arg();

        final QueryQuota.Result result = crmController.queryQuota(queryQuotaArg);
        Assert.assertEquals(AppCenterCodeEnum.PARAM_ILLEGAL_EXCEPTION.getErrCode(), (int) result.getCode());
        Assert.assertNull(result.getQuota());
    }
}
