package com.voting;

import java.io.PrintWriter;

public class PageUtil {
    public static void header(PrintWriter out, String ctx, String title) {
        out.println("<!DOCTYPE html><html><head><meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
        out.println("<title>" + title + "</title>");
        out.println("<link rel='stylesheet' href='" + ctx + "/css/style.css'></head><body>");
        out.println("<div class='navbar'><span class='brand'>&#128499; Online Voting System</span><div>"
                + "<a href='" + ctx + "/index.html'>Home</a>"
                + "<a href='" + ctx + "/vote'>Vote</a>"
                + "<a href='" + ctx + "/results'>Results</a></div></div>");
        out.println("<div class='container'>");
    }

    public static void footer(PrintWriter out) {
        out.println("</div><div class='footer'>Advanced Java Web Project | Nitin Kumar | UID 26MCI10132 | MCA (AI/ML)</div>");
        out.println("</body></html>");
    }
}