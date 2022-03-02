rm -rf target
rm dependency-reduced-pom.xml
ls

git pull --all
project_version=`mvn help:evaluate -Dexpression=project.version -q -DforceStdout`
mvn package -Dmaven.test.skip=true
package_cloud push git-leon/utils ./target/leonium-$project_version.jar --coordinates=com.github.git-leon:leonium:$project_version
