package college;

import io.restassured.response.Response;

public class GetApiResponse {
    /*
     * @className-GetApiResponse
     * @objective- getter setter for the api response entities.
     */
    private Response response;
    private int statusCode;
    private String statusLine;

    //getter and setters for the variable
    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusLine() {
        return statusLine;
    }

    public void setStatusLine(String statusLine) {
        this.statusLine = statusLine;
    }

    //constructor
    public GetApiResponse(Response response, int statusCode, String statusLine) {
        this.response = response;
        this.statusCode = statusCode;
        this.statusLine = statusLine;
    }
}
