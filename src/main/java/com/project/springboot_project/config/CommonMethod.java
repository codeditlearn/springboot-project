package com.project.springboot_project.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@Component
public class CommonMethod {
    @Value("${spring.servlet.multipart.location}") // (절대 경로)application.properties에 정의한 경로를 읽어와서 변수에 넣어줌.
    private String path;
    private final String imgUrl = "http://localhost:8080/images/";
    //private final StringBuffer imgUrl = new StringBuffer("https://file.codeditlearn.com/");
    /* 경고창 */
    public void alert(HttpServletResponse response, String msg){
        try {
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println(msg);
            out.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public Timestamp nowDate(String pattern){
        if(pattern == null){
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        //systemDefaultZone()
        //LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC")).plusHours(9L);
        LocalDateTime nowT = ZonedDateTime.now(ZoneId.of("UTC")).plusHours(9L).toLocalDateTime();
        String dtfm = nowT.format(DateTimeFormatter.ofPattern(pattern));
        Timestamp date = Timestamp.valueOf(dtfm);
        System.out.println("현재시간 : "+ nowT + ", 이것도 현재 시간 : "+date);
        return date;
    }

    public String randomCharacter(int range) {
        StringBuilder sb = new StringBuilder();
        Random rd = new Random();

        for(int i=0;i<range;i++){
            //sb.append((char)(rd.nextInt(26)+65)); //대문자
            sb.append((char)(rd.nextInt(26)+97)); //소문자
        }

        return sb.toString();
    }

    public String fileUpload (MultipartFile file){
        System.out.println("멀티파일 상태 : " + file.getOriginalFilename());
        String result = null;
        if(file == null){
            return result;
        }

        String destinationFileName; //업로드되면서 변경될 이미지 이름
        File destinationFile; //업로드될 이미지 파일 설정
        String destinationFileUrl; //업로드될 이미지 최종 주소값
        //String dbUrl = "http://localhost:8080/images/"; //디비에 들어갈 값
        StringBuffer dbUrl = new StringBuffer("https://file.codeditlearn.com/"); //디비에 들어갈 값

        /* 랜덤 영문 생성*/
        String randomEng = randomCharacter(20);
        String time = nowDate(null).toString().substring(0,10);
        String[] timesplit = time.split("-");
        time = timesplit[0]+timesplit[1]+timesplit[2];
        MultipartFile f = file; //업로드된 파일 정보 가져오기
        String fileName = f.getOriginalFilename(); // 원본 파일명
        String fileFormat = StringUtils.getFilenameExtension(fileName); //파일 확장자명 분리

        if(fileFormat == null){
            return dbUrl.append("thumbnail_null.png").toString(); //<a href="https://www.flaticon.com/kr/free-icons/-" title="이미지 자리 표시 자 아이콘">이미지 자리 표시 자 아이콘 제작자: xnimrodx - Flaticon</a>
            //throw new IllegalStateException("허용되지 않는 파일포맷입니다. : "+fileFormat);
        }
        try {
            //파일 새로운 이름 정하기
            do {
                destinationFileName = "img_"+randomEng +"_"+time+ "." + fileFormat; //임의의 파일 이름
                destinationFile = new File(path, destinationFileName); // 업로드된 파일을 저장할 새 파일 생성
                destinationFileUrl = imgUrl + destinationFileName; //최종업로드된 이미지 주소값
            } while (destinationFile.exists());
            //디비에 업로드될 주소 설정

            /* ++++++++ 파일 업로드 ++++++++ */
            f.transferTo(destinationFile); // 파일 복사해서 경로에 옮기기
            /* ++++++++ 파일 업로드 ++++++++ */

            result = destinationFileUrl;
        }catch(Exception e){
            System.out.println("ERROR : 파일업로드 메서드 에러!!");
            e.printStackTrace();
        }
        System.out.println("파일업로드 결과 : "+result);
        return result;
    }

    public List<String> fileUploadList (List<MultipartFile> file){

        List<String> result = new ArrayList<>();
        //1. 리스트 널 값 체크
        List<MultipartFile> filesList = file.stream().filter(x -> !x.isEmpty()).collect(Collectors.toList());
        //2. 리스트 개수 체크
        int filesCountCheck = filesList.size();
        System.out.println("필터 걸린 업로드 파일 개수 체크 : " + filesCountCheck);
        if(filesCountCheck < 1){
            return result;
        }


        String destinationFileName; //업로드되면서 변경될 이미지 이름
        File destinationFile; //업로드될 이미지 파일 설정
        String destinationFileUrl; //업로드될 이미지 최종 주소값

        for(MultipartFile f : filesList){
            System.out.println("업로드 파일 : " + f.getOriginalFilename());
            /* 랜덤 영문 생성*/
            String randomEng = randomCharacter(20); //영문생성
            String time = nowDate(null).toString().substring(0,10); //날짜생성
            String[] timesplit = time.split("-");
            time = timesplit[0]+timesplit[1]+timesplit[2]; //년,월,일 조합
            String fileName = f.getOriginalFilename(); // 원본 파일명
            String fileFormat = StringUtils.getFilenameExtension(fileName); //파일 확장자명 분리
            try {
                //파일 새로운 이름 정하기
                do {
                    destinationFileName = "img_"+randomEng +"_"+time+ "." + fileFormat; //임의의 파일 이름
                    destinationFile = new File(path, destinationFileName); // 업로드된 파일을 저장할 새 파일 생성
                    destinationFileUrl = imgUrl + destinationFileName; //최종업로드된 이미지 주소값
                } while (destinationFile.exists());
                //디비에 업로드될 주소 설정

                /* ++++++++ 실제 파일 업로드 ++++++++ */
                f.transferTo(destinationFile); // 파일 복사해서 경로에 옮기기
                /* ++++++++ 실제 파일 업로드 ++++++++ */

                result.add(destinationFileUrl); //업로드된 파일 주소를 배열에 추가
            }catch(Exception e){
                System.out.println("ERROR : 파일업로드 메서드 에러!!");
                e.printStackTrace();
            }
        }


        System.out.println("업로드 파일 체크 : " + filesList);
        System.out.println("파일업로드 결과 : "+result);
        return result;
    }
}
