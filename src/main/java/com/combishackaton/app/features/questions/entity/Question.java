package com.combishackaton.app.features.questions.entity;

import com.combishackaton.app.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "questions")
@Data
public class Question extends BaseEntity {
}
