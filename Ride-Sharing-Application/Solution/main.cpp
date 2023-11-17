#include <bits/stdc++.h>
using namespace std;

class Error
{
public:
  string errorMessage;
  int errorCode;

  Error(string message, int code)
  {
    this->errorMessage = message;
    this->errorCode = code;
  }

  void printError()
  {
    cout << "Error encountered :-> status " << this->errorCode << " : " << this->errorMessage << endl;
  }
};

class Person
{
public:
  string name;
};

class Driver : private Person
{
public:
  Driver(string name)
  {
    this->name = name;
  }
};

enum RideStatus
{
  IDLE,
  CREATED,
  WITHDRAWN,
  COMPLETED
};

class Ride
{
private:
  int id, origin, dest, seats;
  RideStatus rideStatus;

public:
  static const int AMT_PER_KM = 20;

  Ride()
  {
    id = origin = dest = seats = 0;
    rideStatus = IDLE;
  }

  double calculateFare(bool isPriorityRider)
  {
    int dist = dest - origin;
    if (seats < 2)
    {
      return dist * AMT_PER_KM * (isPriorityRider ? 0.75 : 1.0);
    }

    return dist * AMT_PER_KM * (isPriorityRider ? 0.5 : 0.75);
  }

  int getId()
  {
    return this->id;
  }
  void setId(int id)
  {
    this->id = id;
  }

  int getOrigin()
  {
    return this->origin;
  }
  void setOrigin(int origin)
  {
    this->origin = origin;
  }

  int getDest()
  {
    return this->dest;
  }
  void setDest(int dest)
  {
    this->dest = dest;
  }

  int getSeats()
  {
    return this->seats;
  }
  void setSeats(int seats)
  {
    this->seats = seats;
  }

  RideStatus getRideStatus()
  {
    return this->rideStatus;
  }
  void setRideStatus(RideStatus rideStatus)
  {
    this->rideStatus = rideStatus;
  }
};

class Rider : private Person
{
private:
  vector<Ride> completedRides;
  Ride currentRide;

public:
  Rider(string name)
  {
    this->name = name;
  }

  void createRide(int id, int origin, int dest, int seats)
  {
    try
    {
      if (origin >= dest)
      {
        Error err = Error("Wrong values of origin and destination are provided.", 400);
        throw(err);
      }

      currentRide.setId(id);
      currentRide.setOrigin(origin);
      currentRide.setDest(dest);
      currentRide.setSeats(seats);
      currentRide.setRideStatus(RideStatus::CREATED);
    }
    catch (Error err)
    {
      err.printError();
    }
  }

  void updateRide(int id, int origin, int dest, int seats)
  {
    try
    {
      if (currentRide.getRideStatus() != RideStatus ::CREATED)
      {
        Error err = Error("Ride can't be updated anymore, as it is not in the right state", 400);
        throw(err);
      }
      createRide(id, origin, dest, seats);
    }
    catch (Error err)
    {
      err.printError();
    }
  }

  void withdrawRide(int id)
  {
    try
    {
      if (currentRide.getId() != id)
      {
        Error err = Error("Ride with this is not the current active ride.", 400);
        throw(err);
      }

      if (currentRide.getRideStatus() != RideStatus ::CREATED)
      {
        Error err = Error("Ride can't be withdrawn anymore.", 400);
        throw(err);
      }

      currentRide.setRideStatus(RideStatus::WITHDRAWN);
    }
    catch (Error err)
    {
      err.printError();
    }
  }

  int closeRide()
  {
    try
    {
      if (currentRide.getRideStatus() != RideStatus ::CREATED)
      {
        Error err = Error("Ride wasn't in progress, can't close rides.", 400);
        throw(err);
      }

      currentRide.setRideStatus(RideStatus::COMPLETED);
      completedRides.push_back(currentRide);
      return currentRide.calculateFare(completedRides.size() >= 10);
    }
    catch (Error err)
    {
      err.printError();
      return 0;
    }
  }
};

int main()
{
  Rider rider("Shahbaz");
  Driver driver("UBER");

  cout << "*****TEST CASE BEGINS******" << endl;
  rider.createRide(1, 50, 60, 1);
  cout << rider.closeRide() << endl;
  rider.updateRide(1, 50, 60, 2);
  cout << rider.closeRide() << endl;
  cout << "*****TEST CASE ENDS******" << endl
       << endl;

  cout << "*****TEST CASE BEGINS******" << endl;
  rider.createRide(1, 50, 60, 1);
  rider.withdrawRide(1);
  rider.updateRide(1, 50, 60, 2);
  cout << rider.closeRide() << endl;
  cout << "*****TEST CASE ENDS******" << endl
       << endl;

  cout << "*****TEST CASE BEGINS******" << endl;
  rider.createRide(1, 50, 60, 1);
  rider.updateRide(1, 50, 60, 2);
  cout << rider.closeRide() << endl;
  cout << "*****TEST CASE ENDS******" << endl
       << endl;
  return 0;
}