package com.example.backend.Board.model;

import com.example.backend.Comment.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BoardDto {
    @Getter
    public static class BoardReg {
        private String title;
        private String content;
        private String writer;
        List<CommentReg> comments = new ArrayList<>();

        public Board toEntity() {
            return Board.builder()
                    .title(title)
                    .content(content)
                    .writer(writer)
                    .build();
        }
    }

    @Getter
    public static class CommentReg {
        private String content;
        private String writer;

        public Comment toEntity(Board board) {
            return Comment.builder()
                    .content(content)
                    .writer(writer)
                    .board(board)
                    .build();
        }
    }

    @Getter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class BoardRes {
        private Long idx;
        private String title;
        private String content;
        private String writer;

        List<CommentRes> comments = new ArrayList<>();

        public static BoardRes from(Board board) {
            return BoardRes.builder()
                    .idx(board.getIdx())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .writer(board.getWriter())
                    .comments(board.getComments().stream().map(CommentRes::from).collect(Collectors.toList()))
                    .build();
        }
    }

    @Getter @NoArgsConstructor @AllArgsConstructor @Builder
    public static class CommentRes {
        private Long idx;
        private String content;
        private String writer;

        public static CommentRes from(Comment comment) {
            return CommentRes.builder()
                    .idx(comment.getIdx())
                    .content(comment.getContent())
                    .writer(comment.getWriter())
                    .build();
        }
    }
}
