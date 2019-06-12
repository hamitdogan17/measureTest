#!/bin/bash

cd /Users/tmobykadioglu/Desktop/TMOB/Measure_Turkcell_Ericcson_Tmob/usecases
java -jar WordToFeature.jar "/Users/tmobykadioglu/Desktop/TMOB/Measure_Turkcell_Ericcson_Tmob/tmob-measure-278253b20811/src/test/resources/stepdefinations/hornetnestbasicusecase.docx"

sleep 5
cd /Users/tmobykadioglu/Desktop/TMOB/Measure_Turkcell_Ericcson_Tmob/tmob-measure-278253b20811
mvn verify
