package com.blog;

import com.google.gson.Gson;
import com.util.ApiSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SukhoController {
    @GetMapping("/sukho")
    public String return_sukho (Model model) {
        return "sukho/index";
    }

    @GetMapping("/sukho/post")
    public String return_post (Model model, @RequestParam(value = "index")String index) throws Exception {
        Gson gson = new Gson();

        //api를 get방식으로 요청한 후 돌려받는 메서드
        //json String형식 또는 'False' 문자열을 리턴한다
        String str = ApiSender.get();
        System.out.println(str);

        PostModel postModel = gson.fromJson(str, PostModel.class);

        Content content = postModel.getContent();

        if (model.getAttribute("title") == null){
            model.addAttribute("title",content.title);
            model.addAttribute("created_at",content.created_at);
            model.addAttribute("content",content.content);
        }

        return "sukho/post";
    }

}