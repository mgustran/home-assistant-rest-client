package com.mgustran.homeassistant.model.converters;

import com.mgustran.homeassistant.model.optimized.HaIntentResponse;
import com.mgustran.homeassistant.model.original.OriginalHaIntentResponse;

public class OriginalIntentResponseToOptimizedConverter {

    public HaIntentResponse convert(final OriginalHaIntentResponse intentResponse) {
        final HaIntentResponse.HaIntentResponseBuilder builder = HaIntentResponse.builder();
        if (intentResponse.getSpeech() != null && intentResponse.getSpeech().getPlain() != null) {
            builder
                    .speech(intentResponse.getSpeech().getPlain().getSpeech())
                    .type("plain")
                    .extra_data(intentResponse.getSpeech().getPlain().getExtraData());
        }
        builder.card(intentResponse.getCard());

        return builder.build();
    }
}
