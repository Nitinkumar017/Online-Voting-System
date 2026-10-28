package com.voting;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ResultServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String ctx = request.getContextPath();
        HttpSession session = request.getSession();

        PageUtil.header(out, ctx, "Results");
        out.println("<div class='card'>");

        if (session.getAttribute("voted") != null) {
            out.println("<p class='msg success'>&#127881; Thank you, <b>" + session.getAttribute("voterName")
                    + "</b>! Your vote for <b>" + session.getAttribute("votedFor")
                    + "</b> was recorded successfully.</p>");
        }

        int total = VoteStore.getTotal();
        out.println("<h2>Live Results</h2>");
        out.println("<p class='sub'>Total votes: <b>" + total + "</b></p>");

        String[] colors = {"#6c5ce7", "#00b894", "#e17055", "#0984e3"};
        int i = 0;
        for (Map.Entry<String, Integer> e : VoteStore.getVotes().entrySet()) {
            int pct = (total == 0) ? 0 : (e.getValue() * 100) / total;
            out.println("<div class='result-row'><div class='result-label'><span>" + e.getKey()
                    + "</span><span>" + e.getValue() + " votes (" + pct + "%)</span></div>");
            out.println("<div class='bar-bg'><div class='bar' style='width:" + pct
                    + "%;background:" + colors[i % colors.length] + "'></div></div></div>");
            i++;
        }

        if (session.getAttribute("voted") == null) {
            out.println("<a class='btn' href='" + ctx + "/vote'>Cast Your Vote</a>");
        }

        out.println("<div class='session-box'><b>Session Info</b><br>Session ID: " + session.getId()
                + "<br>Created: " + new Date(session.getCreationTime())
                + "<br>Last accessed: " + new Date(session.getLastAccessedTime()) + "</div>");
        out.println("</div>");
        PageUtil.footer(out);
    }
}