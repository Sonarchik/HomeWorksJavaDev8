import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.ZoneId;

@WebFilter("/time")
public class TimezoneValidateFilter extends HttpFilter {

    protected void doFilter(HttpServletRequest req,
                            HttpServletResponse res,
                            FilterChain chain) throws IOException, ServletException {
        String timezone = req.getParameter("timezone");
        try {
            timezone = String.join("+", timezone.split(" "));
        } catch (NullPointerException e) {
            timezone = "UTC";
        }
        if (!validTimezone(timezone)) {
            res.sendError(400, "Invalid timezone");
        } else {
            chain.doFilter(req, res);
        }
    }

    private boolean validTimezone(String timezone) {
        try {
            ZoneId.of(timezone);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
