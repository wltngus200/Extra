package com.green.boardextra.modle;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter //무조건 들어감
@Setter //세터 VS 생성자 어느 것 이뮤터블(수정불가)의 문제
@ToString
public class PostBoardReq {//insert에 필요한 정보 모음 BODY
    private String title;
    private String contents;
    private String writer;


    //insert 때는 Auto_increment, current_timestamp 는 신경 쓸 필요 없음
    //아래 3개의 데이터를 프론트로부터 받아야 함


    //데이터를 백엔드로 보낼 때 쓸 수 있는 방법 (이걸로 보내라는 요구)
    //CRUD 무엇이든 사용 가능
    //1. 쿼리스트링(=파라미터)->URL에 데이터를 실어 보냄 / 장점 처리속도, 단점 데이터 노출
    //2. BODY(여러 형태가 있지만 현재단계는 Json과 File만 알고 있으면 됨)
        //에노테이션 설정= 데이터를 쿼리스트링, Json, File로 받을 때에 따라 다르게 생각
    //URL로 데이터를 보내는 방식 중 하나 pathValuable
    //모델 어티리뷰트, 패스베리어블, 쿼리스트링
    //쿼리스트링으로 해서는 절대로 안되는 경우(외에는 모두 가능)
    //1. 데이터가 노출이 되면 안 될때 ex.비밀번호
    //2. 데이터 양이 많을 때
        //URL은 적을 수 있는 양이 제한적 BODY는 데이터 양에 한계가 없는 것에 가깝다

}
