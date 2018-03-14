package resource;

import com.codahale.metrics.annotation.Timed;
import model.Blog;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.List;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class IndexResource {

    @GET
    @Timed
    public List<Blog> index() {
        return Collections.singletonList(new Blog("Day 12: OpenCV--Face Detection for Java Developers",
                "https://www.openshift.com/blogs/day-12-opencv-face-detection-for-java-developers"));
    }

}
