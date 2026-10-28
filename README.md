# Online Voting System

A Java Servlet-based web application for conducting a simple **favourite-subject poll**. Users enter their name, choose one subject, submit a vote, and view live results displayed as vote counts and percentage bars.

> **Project type:** Educational demonstration, not a secure election platform.

## Features

- Vote for one of four subjects: **Python Programming**, **Java Programming**, **Machine Learning**, or **Data Structures**.
- Enter a voter name before submitting a vote.
- Prevent repeated voting **within the same HTTP session**.
- Display live vote totals, percentages, and graphical progress bars.
- Show a voting confirmation and session information (session ID, creation time, and last access time).
- Navigate between Home, Vote, and Results pages.

## Technology Stack

| Component | Technology |
| --- | --- |
| Backend | Java 21, Jakarta Servlet API |
| Server | Apache Tomcat 11 |
| Frontend | HTML, CSS |
| Session tracking | `HttpSession` |
| IDE | Eclipse IDE (Dynamic Web Project) |
| Vote storage | In-memory `LinkedHashMap` (no database) |

The supplied Eclipse `.classpath` targets **JavaSE-21** and **Apache Tomcat v11.0**.

## Project Structure

The following is a recommended Eclipse project layout based on the source files and additional web resources listed for this project:

```text
OnlineVoting/
├── .classpath
├── .project
├── README.md
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── voting/
│       │           ├── PageUtil.java
│       │           ├── ResultServlet.java
│       │           ├── VoteServlet.java
│       │           └── VoteStore.java
│       └── webapp/
│           ├── index.html
│           ├── css/
│           │   └── style.css
│           └── WEB-INF/
│               └── web.xml
└── build/
    └── classes/             # Generated files; do not commit
```

**Note:** `index.html`, `style.css`, `web.xml`, and `MANIFEST.MF` were mentioned in the project file list but were not included among the files reviewed here. Their exact contents and deployment configuration have not been verified. Generated `.class` files are not needed in the GitHub repository.

## Java Classes

| File | Purpose |
| --- | --- |
| `PageUtil.java` | Generates shared HTML header, navigation bar, stylesheet link, and footer. |
| `VoteServlet.java` | Displays the voting form using `doGet()` and processes submissions using `doPost()`. |
| `ResultServlet.java` | Displays vote counts, percentages, confirmation messages, and session information. |
| `VoteStore.java` | Holds the four voting options and synchronized in-memory vote counts. |

## How Voting Works

1. Open the application and visit the **Vote** page.
2. Enter your name and select your favourite subject.
3. Submit the form using an HTTP `POST` request to `/vote`.
4. `VoteServlet` validates the selected option through `VoteStore.addVote()`.
5. If successful, the servlet saves the voting status, chosen option, and name in the HTTP session.
6. The browser redirects to `/results`, where `ResultServlet` shows the updated totals and percentage bars.
7. Returning to `/vote` in the same session displays an **already voted** message.

## Run in Eclipse

### Prerequisites

- JDK 21
- Eclipse IDE with Java Enterprise / Web Tools support
- Apache Tomcat 11

### Steps

1. Clone or download this repository.
2. Open **Eclipse** and choose **File → Import → Existing Projects into Workspace**.
3. Select the `OnlineVoting` project folder and finish importing.
4. Make sure **JDK 21** and **Apache Tomcat 11** are configured in Eclipse.
5. Verify that Java source files are inside `src/main/java/com/voting/` and web resources are under the web application folder.
6. Verify `WEB-INF/web.xml` maps the servlets to `/vote` and `/results` (unless equivalent servlet annotations are configured). The uploaded Java files do **not** contain `@WebServlet` annotations.
7. Right-click the project and select **Run As → Run on Server**.
8. Select **Tomcat 11**, then start the server.
9. Open the application's home page in your browser. If the deployed context path is `OnlineVoting`, the expected address is:

   ```text
   http://localhost:8080/OnlineVoting/
   ```

   The exact context path and port depend on your Eclipse/Tomcat configuration.

### Application Routes

| Route | Purpose |
| --- | --- |
| `/index.html` | Home page (requires the corresponding HTML file) |
| `/vote` | Voting form and submission |
| `/results` | Results and session details |

## Limitations

- **Session-based restriction only:** A different browser, cleared session, or new session can allow another vote. There is no verified voter identity or permanent one-person-one-vote enforcement.
- **No persistent database:** Vote counts are kept in a static Java map and are lost when the application or its classloader restarts.
- **Not production-ready:** The current implementation outputs user-provided names directly into HTML without escaping. Input validation, output encoding, stronger identity checks, and persistent storage are needed for a real deployment.
- Results update when the results page is loaded or refreshed; the supplied code does not implement automatic background updates.

## Suggested Improvements

- Store votes in MySQL or another persistent database.
- Add voter authentication and database-backed duplicate-vote checks.
- Validate and HTML-escape user input.
- Add an admin panel and improved results visualization.

## License

No license file was supplied. Add a `LICENSE` file if you want to specify how others may use or distribute the project.
