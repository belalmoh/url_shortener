# Stage 1: Build the application with Maven
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Set the working directory to the root of the multi-module project inside the container
WORKDIR /project_root

# --- Step 1: Copy all POM files ---
# This layer's cache will be invalidated only if a POM file changes.
# Copy parent POM
COPY pom.xml .
# Copy POMs for all modules to their respective expected directories
# You'll need to list out your modules here.
COPY api_gateway/pom.xml ./api_gateway/pom.xml
COPY config_server/pom.xml ./config_server/pom.xml
COPY discovery_server/pom.xml ./discovery_server/pom.xml
COPY redirection_service/pom.xml ./redirection_service/pom.xml
COPY shortener_service/pom.xml ./shortener_service/pom.xml
# Add any other modules if they exist

# --- Step 2: Download all dependencies for the entire project ---
# This command runs using all the POMs copied above.
# If no POM files changed from the previous build, this layer will be cached.
RUN mvn dependency:go-offline -B -Dmaven.repo.local=/.m2/repository
# The -Dmaven.repo.local=/.m2/repository ensures deps go to a known dir if we mount a volume later

# --- Step 3: Copy all source code and other resources ---
# This layer is invalidated if any source file (or other copied file) changes.
# Since POMs were copied in a previous step, this primarily brings in src/ and other resources.
COPY . .

# --- Step 4: Build the specific module for this Docker image ---
# This layer is rebuilt if source code (from COPY . .) or POMs (from earlier COPY) changed.
# However, if dependencies were cached from Step 2, this step will be faster
# as it won't re-download dependencies.
# Replace '<module-name-in-command>' with the actual module's artifactId or directory name
# (e.g., 'config-server' for config-server/Dockerfile)
RUN mvn clean package -DskipTests -pl discovery_server -am -Dmaven.repo.local=/.m2/repository

# --- Stage 2: Create the runtime image ---
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copy only the built JAR from the specific module's target directory in the builder stage
# Replace '<module-name-in-path>' with the module's directory name
COPY --from=builder /project_root/discovery_server/target/*.jar app.jar

# Expose the port (update for each service)
EXPOSE 8761

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
