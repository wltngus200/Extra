package com.green.boardextra;

import com.green.boardextra.modle.PostBoardReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service //빈등록
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper mapper;

    int postBoard(PostBoardReq p){
        return mapper.insBoard(p);
    }
}
