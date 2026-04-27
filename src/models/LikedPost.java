package models;

public abstract class LikedPost extends Post {

    private int likes = 0;

    public LikedPost(String author){
        super(author);
    }

    @Override
    public String displayCondensed() {
        return super.getAuthor() + " (" + likes + " likes) ";
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void likeAPost(){
        likes++;
    }

    public void unlikeAPost(){
        likes--;
    }

    public String display() {
        String str = super.display();
        str += likes + " likes.\n";
        return str;
    }

}
