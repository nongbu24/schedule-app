## 일정 생성 API

### API 정보

- Method: POST
- URL: /schedules

### Request
```json
{
  "title": "제목",
  "contents": "내용",
  "writer": "작성자",
  "password": "비밀번호"
}
```

### Response
```json
{
  "id": 1,
  "title": "제목",
  "contents": "내용",
  "writer": "작성자",
  "createdAt": "2026-04-12T12:00:00",
  "modifiedAt": "2026-04-12T12:00:00"
}
```

### 설명

- 일정을 생성합니다.

---

## 전체 일정 조회 API

### API 정보

- Method: GET
- URL: /schedules?writer=작성자

### Response
```json
[
    {
      "writer": "작성자",
      "title": "제목",
      "contents": "내용",
      "createdAt": "2026-04-12T12:00:00",
      "modifiedAt": "2026-04-12T12:00:00"
    }
]
```

### 설명

- 작성자를 기준으로 일정을 조회합니다.

---

## 선택 일정 조회 API

- Method: GET
- URL: /schedules/{scheduleId}

### Response
```json
{
  "id": 1,
  "title": "제목",
  "contents": "내용",
  "writer": "작성자",
  "createdAt": "2026-04-12T12:00:00",
  "modifiedAt": "2026-04-12T12:00:00"
}
```

### 설명

- id를 기준으로 일정을 조회합니다.

---

## 일정 수정 API

- Method: PUT
- URL: /schedules/{scheduleId}

### Request
```json
{
  "title": "제목",
  "writer": "작성자",
  "password": "비밀번호"
}
```

### 인증/인가

- password 필요

### Response
```json
{
  "id": 1,
  "title": "제목",
  "contents": "내용",
  "writer": "작성자",
  "createdAt": "2026-04-12T12:00:00",
  "modifiedAt": "2026-04-13T12:00:00"
}
```

### 설명

- id를 선택하여 제목과 작성자명을 수정합니다.
- 입력한 비밀번호가 틀렸다면 수정할 수 없습니다.

---

## 일정 삭제 API

- Method: DELETE
- URL: /schedules/{scheduleId}

### 인증/인가

- password 필요

### Response
```json
{
  "message": "일정 삭제가 완료되었습니다."
}
```

### 설명

- id를 선택하여 일정을 삭제합니다.
- 이 때 비밀번호가 틀렸다면 일정을 삭제할 수 없습니다.

---

## ERD