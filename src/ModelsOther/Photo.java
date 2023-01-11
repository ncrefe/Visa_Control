package ModelsOther;

public class Photo {
    private String resolution;
    private String position;

    public Photo(String[] data) {
        this.resolution = data[2];
        this.position = data[3];
    }

    public Photo(Photo photo) {
        this.resolution = photo.getResolution();
        this.position = photo.getPosition();
    }

    public boolean validResolutionCheck() {
        String[] resolutions = this.resolution.split("x");

        return resolutions[0].equals(resolutions[1]) && Integer.parseInt(resolutions[0]) >= 600
                && Integer.parseInt(resolutions[0]) <= 1200;
    }

    public boolean validPositionCheck() {
        return this.position.equals("Natural Smile") || this.position.equals("Neutral Face");
    }

    public String getResolution() {
        return resolution;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Photo [position=" + position + ", resolution=" + resolution + "]";
    }

}
