package com.techblog.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Auth
    INVALID_PASSWORD(401, "AUTH_001", "비밀번호가 일치하지 않습니다."),
    EXPIRED_TOKEN(401, "AUTH_002", "토큰이 만료되었습니다."),
    INVALID_TOKEN(401, "AUTH_003", "유효하지 않은 토큰입니다."),
    UNAUTHORIZED(401, "AUTH_004", "인증되지 않은 사용자입니다."),
    FORBIDDEN(403, "AUTH_005", "접근 권한이 없습니다."),

    // User
    USER_NOT_FOUND(404, "USER_001", "사용자를 찾을 수 없습니다."),
    DUPLICATE_EMAIL(409, "USER_002", "이미 사용 중인 이메일입니다."),
    DUPLICATE_USERNAME(409, "USER_003", "이미 사용 중인 이름입니다."),

    // Post
    POST_NOT_FOUND(404, "POST_001", "게시글을 찾을 수 없습니다."),
    EMPTY_TITLE(400, "POST_002", "제목은 비어 있을 수 없습니다."),

    // Comment
    COMMENT_NOT_FOUND(404, "COMMENT_001", "댓글을 찾을 수 없습니다."),

    // Common
    INVALID_INPUT(400, "COMMON_001", "유효하지 않은 입력입니다."),
    INTERNAL_ERROR(500, "COMMON_002", "서버 내부 오류가 발생했습니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
