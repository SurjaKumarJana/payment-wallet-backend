package org.surja.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InitTxnPayload implements Serializable {
    private static final long serialVersionUID = 11l;

    private Long id;
    private Long formUserId;
    private Long toUserId;
    private Double amount;
    private String requestId;



}
