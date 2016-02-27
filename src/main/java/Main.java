/**
 * Copyright (c) Lab317, Inc All Rights Reserved.
 */

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * The <code> Main </code>
 * <pre>
 *     協助從網站上下載 我是歌手種子，並直接抓到 NAS 資料裡面，讓 NAS 自動下載。
 * </pre>
 * @author Balicanta.Yao
 * @version 1.0, Created at 2016/1/31.
 */
public class Main {

    /**
     * 目標集數
     */
    private static int TARGET_SERIES_NUM = 7;

    private static String TARGET_URL = "http://tv520.funbbs.me/thread-9166-1-1.html";
    private static String TARGET_PATH = "/Volumes/Download-1/torrentDownload/i-am-singer-"+TARGET_SERIES_NUM+".torrent";
    private static String HOST = "http://tv520.funbbs.me/";

    public static void main(String args[]) throws IOException {
        Document doc = null;

        try {
            doc = Jsoup.connect(TARGET_URL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(doc != null){
            Elements attachments = doc.select(".attachname");

            if(TARGET_SERIES_NUM > attachments.size()){
                System.out.println("Target is " + TARGET_SERIES_NUM);
                System.out.println("Only " + attachments.size() + " Series..");
                return;
            }
            Element attachmentTag = doc.select(".attachname").get(TARGET_SERIES_NUM - 1);
            String href = attachmentTag.child(0).attr("href");

            FileUtils.copyURLToFile(new URL(HOST + href), new File(TARGET_PATH));

        }
    }
}
