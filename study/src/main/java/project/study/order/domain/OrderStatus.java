package project.study.order.domain;

import javax.persistence.Embeddable;

@Embeddable
public enum OrderStatus {
    ORDER,CANCEL
}
