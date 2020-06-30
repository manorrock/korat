#!/bin/bash
for i in `ls ./lib/*.jar`
do
  CLASSPATH=$CLASSPATH:$i
done
exec java -cp $CLASSPATH se.unlogic.eagledns.EagleDNS