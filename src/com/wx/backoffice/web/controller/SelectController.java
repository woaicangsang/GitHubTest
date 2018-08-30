package com.wx.backoffice.web.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wx.backoffice.model.BooksInfo;
import com.wx.backoffice.service.BooksInfoService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("select")
public class SelectController {

    @Autowired
    private BooksInfoService booksInfoService;

    @RequestMapping("ask")
    public String askselect(BooksInfo booksInfo, Model model) {
        System.out.println("++++++++++++" + booksInfo);
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1 = JSONObject.fromObject(booksInfo);
        model.addAttribute("mes",jsonObject1);
        return "user/list";
    }

    @RequestMapping("select")
    @ResponseBody
    public Map<String, List<BooksInfo>> select(BooksInfo mes) {
        Map<String, List<BooksInfo>> mess = new HashMap<>();

        List<BooksInfo> booksInfos = booksInfoService.selectByBooksInfo(mes);

        mess.put("booksInfo",booksInfos);

        return mess;
    }

//    @RequestMapping("select")
//    @ResponseBody
//    public String select(BooksInfo booksInfo, Model model, HttpSession session) {
//
//        BooksInfo booksInfo2 = (BooksInfo) session.getAttribute("booksInfo");
//        if (booksInfo2 != null){
//            booksInfo.setName(booksInfo2.getName());
//            booksInfo.setType(booksInfo2.getType());
//            booksInfo.setAuthor(booksInfo2.getAuthor());
//        }
//        System.out.println("-----------------" +booksInfo);
//        System.out.println("-----------------" +booksInfo2);
//        List<BooksInfo> booksInfos = booksInfoService.selectByBooksInfo(booksInfo);
//        System.out.println(booksInfos);
//
//
//        model.addAttribute("booksInfoList",booksInfos);
//
//        return "user/list";
//    }


    @RequestMapping("delete")
    public String delete(BooksInfo booksInfo, HttpSession session) {

        BooksInfo booksInfo1 = new BooksInfo();
        booksInfo1.setName(booksInfo.getName());
        booksInfo1.setType(booksInfo.getType());
        booksInfo1.setAuthor(booksInfo.getAuthor());
        booksInfoService.deleteByID(booksInfo.getId());
        session.setAttribute("booksInfo",booksInfo1);
        return "forward:select.do";
    }
}
