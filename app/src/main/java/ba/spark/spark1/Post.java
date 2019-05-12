package ba.spark.spark1;


import com.orm.SugarRecord;

public class Post extends SugarRecord<Post> {
    private String username;
    private String timeAgo;
    private int image;
  private   boolean is_liked;
   private int _postId;

    public boolean isIs_liked() {
        return is_liked;
    }
    public Post() {

    }
    public void setIs_liked(boolean is_liked) {
        this.is_liked = is_liked;
    }

    public Post(String username, String timeAgo, int image,boolean is_liked) {
        this.username = username;
        this.timeAgo = timeAgo;
        this.image = image;
    this.is_liked=is_liked;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTimeAgo() {
        return timeAgo;
    }

    public void setTimeAgo(String timeAgo) {
        this.timeAgo = timeAgo;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
