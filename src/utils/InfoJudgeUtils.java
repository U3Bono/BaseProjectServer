package utils;

public class InfoJudgeUtils {

    public static boolean isPhone(String phone) {
        if (phone == null)
            return false;
        if (phone.length() != 11)
            return false;
        for (int i = 0; i < 11; i++) {
            if (phone.charAt(i) < '0' | phone.charAt(i) > '9')
                return false;
        }
        return true;
    }

    public static boolean isMail(String mail) {
        int at = mail.indexOf('@');
        int point = mail.indexOf('.');
        if (at == -1 | point == -1)
            return false;
        String s1 = mail.substring(0, at);
        String s2 = mail.substring(at, point);
        String s3 = mail.substring(point);
        if (s1.length() == 0 | s2.length() == 0 | s3.length() == 0)
            return false;
        return s1.indexOf('@') + s1.indexOf('.') + s2.indexOf('@') + s2.indexOf('.') + s3.indexOf('@') + s3.indexOf('.') == -6;
    }
}
