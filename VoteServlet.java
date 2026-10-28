package com.voting;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class VoteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String ctx = request.getContextPath();
        HttpSession session = request.getSession();

        PageUtil.header(out, ctx, "Vote");
        out.println("<div class='card'>");

        if (session.getAttribute("voted") != null) {
            out.println("<h2>&#9989; You have already voted</h2>");
            out.println("<p class='msg warn'>You voted for <b>" + session.getAttribute("votedFor")
                    + "</b>. Multiple voting is not allowed in one session.</p>");
            out.println("<a class='btn' href='" + ctx + "/results'>View Results</a>");
        } else {
            out.println("<h2>Which is your favourite subject?</h2>");
            out.println("<p class='sub'>Select one option and submit your vote.</p>");
            out.println("<form action='" + ctx + "/vote' method='post'>");
            out.println("<label class='field'>Your Name</label>");
            out.println("<input type='text' name='voterName' placeholder='Enter your name' required>");
            Map<String, Integer> votes = VoteStore.getVotes();
            for (String option : votes.keySet()) {
                out.println("<label class='option'><input type='radio' name='option' value='"
                        + option + "' required><span>" + option + "</span></label>");
            }
            out.println("<button type='submit' class='btn'>Submit Vote</button>");
            out.println("</form>");
        }
        out.println("</div>");
        PageUtil.footer(out);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("voted") != null) {
            response.sendRedirect(request.getContextPath() + "/vote");
            return;
        }

        String name = request.getParameter("voterName");
        String option = request.getParameter("option");

        if (option != null && VoteStore.addVote(option)) {
            session.setAttribute("voted", true);
            session.setAttribute("votedFor", option);
            session.setAttribute("voterName", name);
        }
        response.sendRedirect(request.getContextPath() + "/results");
    }
}