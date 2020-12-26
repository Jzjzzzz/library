package util;

public class PathUtils {

    private static final String P_PATH="..\\book_library\\images\\";

    public static String getRealPath(String relativePath){

        return P_PATH+relativePath;
    }

}
