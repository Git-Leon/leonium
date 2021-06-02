git pull --all
mvn package -Dmaven.test.skip=true
package_cloud push git-leon/utils ./target/leonium-2.1.5.jar --coordinates=com.github.git-leon:leonium:2.1.5
