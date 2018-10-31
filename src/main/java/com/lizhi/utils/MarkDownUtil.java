package com.lizhi.utils;

import com.google.common.base.Joiner;
import com.vladsch.flexmark.ast.Content;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.options.MutableDataSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class MarkDownUtil {

    private static String MD_CSS = null;

    static {
        try {
            InputStream stream = new MarkDownUtil().getClass().getClassLoader().getResourceAsStream("md/huimarkdown.css");
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "utf-8"));
            List list = reader.lines().collect(Collectors.toList());
            String content = Joiner.on(" ").join(list);// markdown to image
            MD_CSS = content;
            MD_CSS = "<style type=\"text/css\">\n" + MD_CSS + "\n</style>\n";
        } catch (Exception e) {
            MD_CSS = "";
        }
    }

    /**
     * 将本地的markdown文件，转为html文档输出
     *
     * @param path 相对地址or绝对地址 ("/" 开头)
     * @return
     * @throws IOException
     */
    private static MarkdownEntity ofFile(String path) throws IOException {
        InputStream stream = new MarkDownUtil().getClass().getClassLoader().getResourceAsStream(path);
        return ofStream(stream);
    }



//    /**
//     * 将网络的markdown文件，转为html文档输出
//     *
//     * @param url http开头的url格式
//     * @return
//     * @throws IOException
//     */
//    public static MarkdownEntity ofUrl(String url) throws IOException {
//        return ofStream(FileReadUtil.getStreamByFileName(url));
//    }


    /**
     * 将流转为html文档输出
     *
     * @param stream
     * @return
     */
    private static MarkdownEntity ofStream(InputStream stream) {
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(stream, Charset.forName("UTF-8")));
        List<String> lines = bufferedReader.lines().collect(Collectors.toList());
        String content = Joiner.on("\n").join(lines);
        return ofContent(content);
    }


    /**
     * 直接将markdown语义的文本转为html格式输出
     *
     * @param content markdown语义文本
     * @return
     */
    private static MarkdownEntity ofContent(String content) {
        MarkdownEntity entity = new MarkdownEntity();
        entity.setCss(MD_CSS);
        entity.setHtml(parse(content));
        entity.addDivStyle("class", "markdown-body ");
        return entity;
    }


    /**
     *
     * @param content markdown contents
     * @return parse html contents
     */
    private static String parse(String content) {
        MutableDataSet options = new MutableDataSet();
        options.setFrom(ParserEmulationProfile.MARKDOWN);

        // enable table parse!
        options.set(Parser.EXTENSIONS, Arrays.asList(TablesExtension.create()));


        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        Node document = parser.parse(content);
        return renderer.render(document);
    }

    public static String markDownToHtml(String content){
       return ofContent(content).toString();
    }

    public static void main(String[] args) {
        String s = MarkDownUtil.markDownToHtml("[01 - Getting Started](quiver-note-url/D2A1CC36-CC97-4701-A895-EFC98EF47026)");
        System.out.println(s);
    }
}
