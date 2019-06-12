#!/bin/bash

cd C:/Users/ehamdog/Desktop/Measure_Finaly/Measure_Turkcell_Ericcson_Tmob/usecases
java -jar WordToFeature.jar "C:/Users/ehamdog/Desktop/Measure_Finaly/Measure_Turkcell_Ericcson_Tmob/tmob_measure/src/test/resources/stepdefinations/hornetnestbasicusecase.docx"

sleep 3
cd C:/Users/ehamdog/Desktop/Measure_Finaly/Measure_Turkcell_Ericcson_Tmob/tmob_measure
mvn verify
