import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String timezone = req.getParameter("timezone");

        try {
            timezone = String.join("+", timezone.split(" "));
        } catch (NullPointerException e) {
            timezone = "UTC";
        }

        LocalDateTime currentTime = LocalDateTime.now(ZoneId.of(timezone));
        String formatDateTime = currentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().write(formatDateTime + " UTC");
        resp.getWriter().close();
    }
}
