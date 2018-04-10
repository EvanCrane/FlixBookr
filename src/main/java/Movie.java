package main.java;
import java.time.LocalDateTime;

public class Movie
{
    //Instance Variables
    private String title;
    private String rating;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime[] showTimes;
    private Byte[] promoImage;
    private String description;

    //Constructor
    public Movie(String title, String rating, LocalDateTime startTime, LocalDateTime endTime,
                 LocalDateTime[] showTimes, Byte[] promoImage, String description) {
        this.title = title;
        this.rating = rating;
        this.startTime = startTime;
        this.endTime = endTime;
        this.showTimes = showTimes;
        this.promoImage = promoImage;
        this.description = description;
    }

    //Properties
    ///Title
    public String getTitle() {
        return this.title;
    }

    public void setNumber(String title) {
        this.title = title;
    }

    ///Rating
    public String getRating() {
        return this.rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    ///StartTime
    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    ///EndTime
    public LocalDateTime getEndTime() {
        return this.endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    ///ShowTimes
    public LocalDateTime[] getShowTimes() {
        return this.showTimes;
    }

    public void setShowTime(LocalDateTime[] showTimes) {
        this.showTimes = showTimes;
    }

    ///PromoImage
    public Byte[] getPromoImage() {
        return this.promoImage;
    }

    public void setPromoImage(Byte[] promoImage) {
        this.promoImage = promoImage;
    }

    ///Description
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
