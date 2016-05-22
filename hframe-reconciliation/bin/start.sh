APP_HOME=${PWD}
LIB_PATH=$APP_HOME/lib
for i in $LIB_PATH/*
    do CLASSPATH=$i:$CLASSPATH
done
CLASSPATH=$CLASSPATH:$APP_HOME/conf
JAVA_OPTS="-Xms1024m -Xmx2048m -XX:MaxPermSize=1024m"
echo "clsspath-----------------------"
echo $CLASSPATH
nohup java $JAVA_OPTS -classpath $CLASSPATH com.hframework.reconciliation.Bootstrap >log/nohup.out 2>&1 &
#java $JAVA_OPTS -classpath $CLASSPATH  -javaagent:lib/aspectjweaver-1.8.6.jar -Djava.library.path=native com.ucfgroup.face.Bootstra
p
echo $! >  ./run/server.pid