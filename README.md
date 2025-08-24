# RGT-Task-2-TrainingManagementApp-SpringMVC

Training Management App using Spring MVC without a Database.

The Training Management System is a Spring MVC REST API that helps manage User training within an Organization. Here we perform some operations/methods, such as:

1.Creating userEntity(user id,user name,user role)
2.Creating trainingEntity(trainingid,training title,training due date,status of training)
3. Assigning training to individual users and multiple users.
4. Mark the Training as Completed.
5. Get overdue trainings (past due date and still pending)
The project uses in-memory storage (List) instead of a database.

API's:(Controller Layer):

 1.AddUsers-->Adding users using id, name, role--> We use POST method-->/api/addusers.
 2.GetAllUsers-->getting all users -->We use GET method --->/api/getusers.
 3.AddTrainings--->adding trainings using id,title,duedate and status--->POST Method-->/api/addtrainings 
 4.GetAllTrainings--> getting all trainings-->We use GET method--->/api/gettrainings
 5.AssignTrainingToIndividualUser--> we will assign training to individual user(tid ,uid)--->POST method-->/api/assign   
 6.AssignTrainingtoMultipleUsers-->we will assign training to multiple users(1-tid,n-uid)-->POST method--->/api/trainings/{trainingId}/assign 
 7. Get training for a user-->we will get training for a user --->GET method--->/api/user/{userId}
 8.Mark Training as Completed--->We mark pending status as completed-->PUT method -->/api/{trainingId}/users/{userId}/complete 
 9. Get Overdue Trainings for a user-->we have to get overdue to pending even if the due date is completed and status is still pending -->GET method-->/api/user/{userId}/overdue

