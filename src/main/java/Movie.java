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
    private String promoImage;
    private String description;

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
    public String getPromoImage() {
        return this.promoImage;
    }

    public void setPromoImage(String promoImage) {
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
