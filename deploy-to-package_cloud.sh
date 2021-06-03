rm -rf target
rm *jar
rm dependency-reduced-pom.xml
ls

project_version=`mvn help:evaluate -Dexpression=project.version -q -DforceStdout`
git pull --all
mvn package -Dmaven.test.skip=true
package_cloud push git-leon/utils ./target/leonium-$project_version.jar --coordinates=com.github.git-leon:leonium:$project_version