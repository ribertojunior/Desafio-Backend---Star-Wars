package com.example.starwars.events;

import com.example.starwars.model.Planeta;
import com.example.starwars.service.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class PlanetaListener extends AbstractMongoEventListener<Planeta> {
  private final SequenceGeneratorService sequenceGenerator;

  @Autowired
  public PlanetaListener(SequenceGeneratorService sequenceGenerator) {
    this.sequenceGenerator = sequenceGenerator;
  }

  @Override
  public void onBeforeConvert(BeforeConvertEvent<Planeta> event) {
    if (event.getSource().getId() == null || event.getSource().getId() < 1) {
      event.getSource().setId(sequenceGenerator.generateSequence(Planeta.SEQUENCE_NAME));
    }
  }
}
