package com.green.boardextra;

import com.green.boardextra.modle.PostBoardReq;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController //빈등록
@RequiredArgsConstructor
@RequestMapping("api/board")//프론트와 주소가 겹치면 문제 발생 가능성이 있어 분리 해줌
public class BoardController {
    private final BoardService service;

    //BODY들만 컨텐트 타입이 중요 <-> URL에서 데이터를 끄집어냄
    @PostMapping //분기는 메소드로 하고 주소값은 웬만하면 통일
            //에노테이션=스프링='Json형태로 데이터로 보내라'라는 요구 BODY에 실리는 JSON을 받기 위함
            //BODY에 실리는 건 @RequestPart Multi file이면 줄 때도 받을 때도 RequestPart
    public int postBoard(@RequestBody PostBoardReq p){
        return service.postBoard(p);
    }
    //파일 올리기
    @PostMapping("file")//주소값과 http메소드로만 분기 (파라미터와 관계 없다!)
    public int postBoardFile(@RequestPart MultipartFile pic, @RequestPart PostBoardReq p){
        //여러장의 사진을 받고 싶을 땐 리스트+postman에 pic을 여러개
        log.info("pic: {}",pic);
        //pic.getOriginalFilename(); :실제 파일의 이름
        //여러개 넣어도 에러는 안 나지만 1개만 받아온다
        log.info("p: {}", p);
        return 0;
    }
    //BeanCreationException 빈등록을 하기에 분기문을 만들수 없어 에러
    //Ambiguous mapping 애매한 매핑

    //@RequestParam (page+size)
    @GetMapping()
    public int getBoard(@RequestParam int page, @RequestParam int size){
                    //무조건 받았으면 좋겠을 때
        log.info("page: {}",page);
        log.info("size: {}",size);
        return 1;
    }
    //브라우저로 작업
    //http://localhost:8080/api/board -> 아무것도 안 띄워줌
    //Required parameter 'size' is not present.
    //@RequestParam은 required임 무조건 값을 보내야함
    //데이터를 보내는 로직은 없기 때문에 그냥 리턴 값 출력 String으로 바꿔도 출력 가능

    @DeleteMapping //그냥 구분 짓기 위해
    public int deleteBoard(@RequestParam(required = false)int page, @RequestParam int size) {
        //required를 바꾸면 꼭 보내지 않아도 되지만 문제는  primitive type은 안됨 500에러
        //안 보내도 되지만 null값이 저장되지 않아서 안 됨이라는 에러 int->Integer로 변경시 가능
        return 1;
    }

    @GetMapping("req")
    public int getBoardReq(HttpServletRequest req){
        String strPage=req.getParameter("page");//String으로 리턴
        //프론트 컨트롤러(서블릿)이라는 애가 이 작업을 자동적으로 해주는데
        //왓스라는 애가 이런 데이터가 넘어왔네? 하면서 정리해서 객체에 담아 전해줌
        int page=0;
        if(strPage!=null){
            page=Integer.parseInt(strPage);
        }
        log.info("page: {}", page);
        log.info("strPage: {}", strPage);
        //http://localhost:8080/api/board/req?page=1  * page=1 strPage=1
        //http://localhost:8080/api/board/req * page=0 strPage=null
        return 12;
    }

    //@Model
    @GetMapping("model")
    public int getBoardModel(@ModelAttribute BoardGetReq p){
        //페이지와 사이즈 따로따로 처리 안 하고 객체로 처리 하고 싶다
        log.info("p: {}", p);
        return 15;
    }
    //model로 주소가 끝나도 페이지, 사이즈에 0,0이 넘어옴  null이면 아무것도 안 넣어줌 위에
    //값을 넣어주면 형변환 해서 값을 넣어줌

    @GetMapping("model2")
    public int getBoardModel2(@ParameterObject @ModelAttribute BoardGetReq p) {
                            //스웨거에서 출력될 때 다르다 //모델어트리뷰트 생략해도 상관 없음
                            //변수 명(p)이 나오던 게 page, size로 출력
        //페이지와 사이즈 따로따로 처리 안 하고 객체로 처리 하고 싶다
        log.info("p: {}", p);
        return 15;
    }
}


@Getter
@Setter
@ToString
class BoardGetReq{
    private int page;
    private int size;
}