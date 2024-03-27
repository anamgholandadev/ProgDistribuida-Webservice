package br.ufrn.imd.webservice.service;

import org.springframework.stereotype.Service;

import java.io.File;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;

@Service
public class ChatService {
    private Chat chatSession;

    public ChatService() {
        try {
            String resourcesPath = getResourcesPath();
            Bot bot = new Bot("super", resourcesPath);
            this.chatSession = new Chat(bot);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getChatResponse(String message) {
        String response = chatSession.multisentenceRespond(message);
        response = response.replace("&lt;", "<").replace("&gt;", ">");
        return response;
    }

    private static String getResourcesPath() {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        path = path.substring(0, path.length() - 2);
        System.out.println(path);
        String resourcesPath = path + File.separator + "src" + File.separator + "main" + File.separator + "resources";
        return resourcesPath;
    }
}
