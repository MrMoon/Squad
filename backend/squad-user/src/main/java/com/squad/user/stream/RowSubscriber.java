package com.squad.user.stream;

import io.confluent.ksql.api.client.Row;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@NoArgsConstructor
@CommonsLog(topic = "Row Subscriber Logger")
public class RowSubscriber implements Subscriber<Row> {

    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        log.info("Subscriber is subscribed");
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(Row row) {
        log.info("Row: " + row.values());
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        log.error(throwable);
    }

    @Override
    public void onComplete() {
        log.info("Query has ended");
    }
}
