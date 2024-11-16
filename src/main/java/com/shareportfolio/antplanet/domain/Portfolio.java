package com.shareportfolio.antplanet.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 포트폴리오 고유 ID

    @Column(nullable = false)
    private String stockName; // 주식 이름

    @Column(nullable = false)
    private int shares; // 보유 주식 수량

    @Column(nullable = false)
    private double value; // 주식 가치

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member; // 포트폴리오 소유 회원
}