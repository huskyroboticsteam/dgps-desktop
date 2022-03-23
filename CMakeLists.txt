# Minimum version of CMake required. This will determine what features you can use; 3.8 is a
# reasonably new enough version that we aren't missing features from later versions, but old
# enough so that we are still preserving some backwards compatibility.
cmake_minimum_required(VERSION 3.8)

# Set C++ standard version to C++17; will allow you to use features such as std::optional,
# etc. The JSON library requires at least C++11.
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# Defines the project, including its name, what programming languages it uses, and the
# version. Incrememt version number according to https://semver.org/ as you make changes.
project(DGPSDesktop
  LANGUAGES CXX
  VERSION 0.0.0)

# Find the WebSocket++ library and Boost (provides the ASIO backend for Websocket++ and also
# provides the program_options argument parser). Only the `system` component of Boost is
# currently required.
find_package(websocketpp REQUIRED)
find_package(Boost REQUIRED COMPONENTS system)

# Find the JSON library
find_package(nlohmann_json 3.2.0 REQUIRED)

# Find the GPS library
find_package(UbloxLinux REQUIRED)

# Find the system threads library
find_package(Threads REQUIRED)

# Add an executable to the project called "dgps-base-station", compiled from the following list
# of source files. If you add more files to the project, add them to this list.
add_executable(dgps-base-station
  src/main.cpp)

# Link the executable with all of the libraries it depends on.
target_link_libraries(dgps-base-station
  Threads::Threads
  ${Boost_LIBRARIES}
  websocketpp::websocketpp
  nlohmann_json::nlohmann_json
  UbloxLinux::ublox_linux)
