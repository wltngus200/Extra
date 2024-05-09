package com.green.boardextra;

import com.green.boardextra.modle.PostBoardReq;
import org.apache.ibatis.annotations.Mapper;

@Mapper //빈등록 : 없으면 DI해 줄수 있는게 없다고 경고 띄움
public interface BoardMapper {
    int insBoard(PostBoardReq p);
}
