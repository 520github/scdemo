rootPath=/Users/sunso520/code/IdeaProjects/cloud/demo/
buildPath=/build/libs/
targetPath=/Users/sunso520/code/IdeaProjects/cloud/demo/docker/
buildProject="eureka-server,zuul-server,greeting-service,consumer-service"

if [ -n "$1" ];then
    buildProject=$1
fi

buildProject=${buildProject//,/  }
for project in $buildProject
do 
      echo $project
      rm -f $targetPath$project"/.jar"
      projectPath=$rootPath$project
      cd $projectPath$buildPath
      rm -f *
      cd $projectPath
      gradle build
      cd $projectPath$buildPath
      cp *.jar  $targetPath$project".jar"
done

cd $targetPath

docker stop $(docker ps -q)
docker rm $(docker ps -q)

docker-compose up -d


