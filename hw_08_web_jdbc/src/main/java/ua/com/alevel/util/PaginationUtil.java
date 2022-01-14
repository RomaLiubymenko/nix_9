package ua.com.alevel.util;

import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;
import ua.com.alevel.dto.response.PageData;
import ua.com.alevel.dto.response.ResponseDto;

public final class PaginationUtil {

    private PaginationUtil() {
    }

    public static <REQ extends ResponseDto> HttpHeaders generatePaginationHttpHeaders(PageData<REQ> page, String baseUrl) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", Long.toString(page.getTotalElements()));
        String link = "";
        if ((page.getPageNumber() + 1) < page.getTotalPages()) {
            link = "<" + generateUri(baseUrl, page.getPageNumber() + 1, page.getPageSize()) + ">; rel=\"next\",";
        }
        // prev link
        if ((page.getPageNumber()) > 0) {
            link += "<" + generateUri(baseUrl, page.getPageNumber() - 1, page.getPageSize()) + ">; rel=\"prev\",";
        }
        // last and first link
        int lastPage = 0;
        if (page.getTotalPages() > 0) {
            lastPage = page.getTotalPages() - 1;
        }
        link += "<" + generateUri(baseUrl, lastPage, page.getPageSize()) + ">; rel=\"last\",";
        link += "<" + generateUri(baseUrl, 0, page.getPageSize()) + ">; rel=\"first\"";
        headers.add(HttpHeaders.LINK, link);
        return headers;
    }

    private static String generateUri(String baseUrl, int page, int size) {
        return UriComponentsBuilder.fromUriString(baseUrl).queryParam("page", page).queryParam("size", size).toUriString();
    }

    public static <REQ extends ResponseDto> HttpHeaders generateSearchPaginationHttpHeaders(String query, PageData<REQ> page, String baseUrl) {
        String escapedQuery = query.replace(",", "%2C");
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", Long.toString(page.getTotalElements()));
        String link = "";
        if ((page.getPageNumber() + 1) < page.getTotalPages()) {
            link = "<" + generateUri(baseUrl, page.getPageNumber() + 1, page.getPageSize()) + "&query=" + escapedQuery + ">; rel=\"next\",";
        }
        // prev link
        if ((page.getPageNumber()) > 0) {
            link += "<" + generateUri(baseUrl, page.getPageNumber() - 1, page.getPageSize()) + "&query=" + escapedQuery + ">; rel=\"prev\",";
        }
        // last and first link
        int lastPage = 0;
        if (page.getTotalPages() > 0) {
            lastPage = page.getTotalPages() - 1;
        }
        link += "<" + generateUri(baseUrl, lastPage, page.getPageSize()) + "&query=" + escapedQuery + ">; rel=\"last\",";
        link += "<" + generateUri(baseUrl, 0, page.getPageSize()) + "&query=" + escapedQuery + ">; rel=\"first\"";
        headers.add(HttpHeaders.LINK, link);
        return headers;
    }
}
