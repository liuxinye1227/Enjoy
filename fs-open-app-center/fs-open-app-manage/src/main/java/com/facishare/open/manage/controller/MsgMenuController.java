//package com.facishare.open.manage.controller;
//
//import com.facishare.open.manage.ajax.result.AjaxResult;
//import com.facishare.open.manage.model.MenuPageVO;
//import com.facishare.open.manage.model.TreeVO;
//import com.facishare.open.manage.model.TreeVOByIdComparator;
//import com.facishare.open.manage.model.TreeVOByPIdComparator;
//import com.facishare.open.manage.utils.LocalCache;
//import com.facishare.open.manage.utils.MenuFormatter;
//import com.facishare.open.msg.model.CustomMenuVO;
//import com.facishare.open.msg.service.MsgSessionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.List;
//
//
//@Controller
//@RequestMapping("/open/manage/rest/appmenu")
//public class MsgMenuController extends BaseController {
//
//    @Autowired
//    MsgSessionService msgSessionService;
//
//    /**
//     * 菜单页面
//     *
//     * @return
//     */
//    @RequestMapping("menu")
//    @ResponseBody
//    public AjaxResult menudisplay() {
//
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("menu/menupage");
//
//        LocalCache.menuCache.remove("FSAID_1993983");
//
//        return new AjaxResult(mv);
//    }
//
//    @RequestMapping("initMenu")
//    @ResponseBody
//    public AjaxResult initMenu(@RequestBody String appId) throws Exception {
//
//        MenuPageVO menuPageVOCache = LocalCache.menuCache.get(appId);
//        List<TreeVO> treeList = menuPageVOCache.getTreeVOList();
//
//        /*if (null != treeList) {
//          //对菜单按照Id升序进行排序
//            Comparator<TreeVO> byId = new TreeVOByIdComparator();
//            Collections.sort(treeList, byId);
//        }*/
//
//        return new AjaxResult(treeList);
//    }
//
//    /**
//     * 获取菜单id
//     *
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping("getMenuId")
//    @ResponseBody
//    public AjaxResult getMenuId(@RequestBody String appId) throws Exception {
//
//        TreeVO treeVO = new TreeVO();
//        MenuPageVO menuPageVOCache = LocalCache.menuCache.get(appId);
//        if (null == menuPageVOCache) {
//
//            treeVO.setId("1");
//        } else {
//            String maxMenuId = "1";
//            List<TreeVO> treeCacheList = menuPageVOCache.getTreeVOList();
//
//            if (null != treeCacheList && treeCacheList.size() > 0) {
//
//                Comparator<TreeVO> byId = new TreeVOByIdComparator();
//                Collections.sort(treeCacheList, Collections.reverseOrder(byId));
//                String maxId = treeCacheList.get(0).getId();
//
//                Comparator<TreeVO> byPId = new TreeVOByPIdComparator();
//                Collections.sort(treeCacheList, Collections.reverseOrder(byPId));
//
//                String maxPId = treeCacheList.get(0).getpId();
//
//                maxMenuId = (Integer.parseInt(maxId) >= Integer.parseInt(maxPId)) ? maxId : maxPId;
//                maxMenuId = String.valueOf(Integer.parseInt(maxMenuId) + 1);
//
//                treeVO.setId(maxMenuId);
//            } else {
//
//                treeVO.setId("1");
//                ;
//            }
//        }
//
//        return new AjaxResult(treeVO);
//    }
//
//    /**
//     * 添加一级菜单
//     *
//     * @param menuPageVO
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping("addFirstMenu")
//    @ResponseBody
//    public AjaxResult addFirstMenu(@RequestBody MenuPageVO menuPageVO) throws Exception {
//
//        List<TreeVO> treeList = menuPageVO.getTreeVOList();
//
//        if (null == treeList || treeList.size() == 0) {
//
//            treeList = new ArrayList<TreeVO>();
//        } else {
//            //对菜单按照Id升序进行排序
//            Comparator<TreeVO> byId = new TreeVOByIdComparator();
//            Collections.sort(treeList, byId);
//        }
//
//        MenuPageVO menuResult = new MenuPageVO();
//        menuResult.setAppId(menuPageVO.getAppId());
//        menuResult.setName(menuPageVO.getName());
//        menuResult.setTreeVOList(treeList);
//
//        LocalCache.menuCache.put(menuPageVO.getAppId(), menuResult);
//
//        return new AjaxResult(treeList);
//    }
//
//    /**
//     * 添加二级菜单
//     *
//     * @param menuPageVO
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping("addChildMenu")
//    @ResponseBody
//    public AjaxResult addChildMenu(@RequestBody MenuPageVO menuPageVO) throws Exception {
//
//        MenuPageVO menuPageVOResult = menuPageVO;
//
//        MenuPageVO menuPageVOCache = LocalCache.menuCache.get(menuPageVO.getAppId());
//
//        List<TreeVO> treeVOList = menuPageVOCache.getTreeVOList();
//
//        if (null != treeVOList && treeVOList.size() > 0) {
//
//            for (TreeVO treeVO : menuPageVOResult.getTreeVOList()) {
//
//                boolean isExist = false;
//
//                for (TreeVO item : treeVOList) {
//
//                    if (treeVO.getId().equals(item.getId()) && treeVO.getpId().equals(item.getpId())) {
//                        isExist = true;
//                        break;
//                    }
//                }
//
//                if (!isExist) {
//                    treeVOList.add(treeVO);
//                }
//            }
//            menuPageVOResult.setTreeVOList(treeVOList);
//        }
//        LocalCache.menuCache.remove(menuPageVO.getAppId());
//        LocalCache.menuCache.put(menuPageVO.getAppId(), menuPageVOResult);
//
//        return new AjaxResult(menuPageVOResult.getTreeVOList());
//    }
//
//    /**
//     * 保存菜单   发送
//     *
//     * @param menuPageVO
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping("saveMenu")
//    @ResponseBody
//    public AjaxResult saveMenu(@RequestBody MenuPageVO menuPageVO) throws Exception {
//
//        MenuPageVO menuPageVOResult = menuPageVO;
//
//        MenuPageVO menuPageVOCache = LocalCache.menuCache.get(menuPageVO.getAppId());
//
//        List<TreeVO> treeVOList = menuPageVOCache.getTreeVOList();
//
//        if (null != treeVOList && treeVOList.size() > 0) {
//
//            for (TreeVO treeVO : menuPageVOResult.getTreeVOList()) {
//
//                boolean isExist = false;
//
//                for (TreeVO item : treeVOList) {
//
//                    if (treeVO.getId().equals(item.getId()) && treeVO.getpId().equals(item.getpId())) {
//
//                        treeVOList.remove(item);
//                        treeVOList.add(treeVO);
//                        isExist = true;
//                        break;
//                    }
//                }
//
//                if (!isExist) {
//                    treeVOList.add(treeVO);
//                }
//            }
//            menuPageVOResult.setTreeVOList(treeVOList);
//        }
//        LocalCache.menuCache.remove(menuPageVO.getAppId());
//        LocalCache.menuCache.put(menuPageVO.getAppId(), menuPageVOResult);
//
//        //CustomMenuVO customMenuVO = MenuFormatter.getJsonStr(menuPageVOResult, "FSAID_1993983", "XXXXXXXXXXXX");
//        //msgSessionService.updateCustomMenu(customMenuVO);
//
//        return new AjaxResult(menuPageVOResult.getTreeVOList());
//    }
//
//    @RequestMapping("sendMenuMsg")
//    @ResponseBody
//    public AjaxResult sendMenuMsg(@RequestBody MenuPageVO menuPageVO) throws Exception {
//
//        MenuPageVO menuPageVOResult = menuPageVO;
//        CustomMenuVO customMenuVO = MenuFormatter.getJsonStr(menuPageVOResult, menuPageVOResult.getAppId(), menuPageVOResult.getName());
//        msgSessionService.updateCustomMenu(customMenuVO);
//
//        return new AjaxResult(menuPageVOResult.getTreeVOList());
//    }
//
//    /**
//     * 删除菜单
//     *
//     * @param menuPageVO
//     * @return
//     * @throws Exception
//     */
//    @RequestMapping("deleteMenu")
//    @ResponseBody
//    public AjaxResult deleteMenu(@RequestBody MenuPageVO menuPageVO) throws Exception {
//
//        MenuPageVO menuPageVOResult = menuPageVO;
//        LocalCache.menuCache.put(menuPageVO.getAppId(), menuPageVOResult);
//        return new AjaxResult(menuPageVOResult.getTreeVOList());
//    }
//}