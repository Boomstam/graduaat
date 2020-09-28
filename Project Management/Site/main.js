class Task{
    constructor(domain, name, actions, deadline, importance, finished){
        this.domain = domain;
        this.name = name;
        this.actions = actions;
        this.deadline = deadline;
        this.importance = importance;
        this.finished = finished;
    }
}

var tasks = [];
var htmlItems = [];
var downloadLink;

startApp();

function startApp(){

    createUploadLink();
    createDownloadLink();
    drawAddTask();
    reDraw();

    function drawAddTask()
    {
        let addButton = document.createElement('button');
        addButton.innerHTML = "+";
        addButton.className = "addButton"
        document.body.appendChild(addButton);

        addButton.onclick = function(){
            console.log("clicked");
            newEmptyTask();
        }
        
        function newEmptyTask(){
            tasks = tasks.concat(new Task());

            reDraw();
        }
    }
}

    function createUploadLink(){
        let uploadLink = document.createElement('input');
        uploadLink.innerHTML = "Upload JSON";
        uploadLink.type = 'file';
        uploadLink.oninput = function inputReceived(){
            let jsonFile = uploadLink.files[0];
            console.log(jsonFile);
            jsonFile.text().then(successCallback, failureCallback);
            
            function successCallback(result) {
                console.log(result);
                let tasksObjColl = JSON.parse(result);
                console.log(tasksObjColl);
                let numTasks = tasksObjColl.length;
                tasks = new Array(numTasks);
                for (i = 0; i < numTasks; i++) {
                    let taskObj = tasksObjColl[i];
                    tasks[i] = new Task(taskObj.domain, taskObj.name, taskObj.actions, taskObj.deadline, taskObj.importance, taskObj.finished);
                }
                reDraw();
            }
              
            function failureCallback(error) {
                console.error("Error reading file: " + error);
            }
        }
        //htmlItems = htmlItems.concat(uploadLink);
        document.body.appendChild(uploadLink);
    }

    function createDownloadLink(){
        downloadLink = document.createElement('a');
        downloadLink.innerHTML = "Download JSON"
        downloadLink.setAttribute("href", "NONE");
        downloadLink.onclick = function updateDownloadLink(){
            let dataStr = "data:text/json;charset=utf-8," + encodeURIComponent(JSON.stringify(tasks));
            downloadLink.setAttribute("href", dataStr);
            downloadLink.setAttribute("download", "scene.json");
        }
        //htmlItems = htmlItems.concat(downloadLink);
        document.body.appendChild(downloadLink);
    }

function removeElement(element) {
    if(element === null || element.parentNode === null){
        return;
    }
    element.parentNode.removeChild(element);
}

function reDraw(){
    for (i = 0; i < htmlItems.length; i++) {
        removeElement(htmlItems[i]);
    }
    htmlItems = [];

    for (i = 0; i < tasks.length; i++) {
        drawTask(tasks[i]);
    }
    function drawTask(task){

        let taskHolder = document.createElement('section');
        taskHolder.className = "taskHolder";

        let domain = document.createElement('input');
        if(task.domain === undefined){
            domain.placeholder = "Domain";
        } else{
            domain.value = task.domain;
        }
        domain.oninput = function domainInput(){
            task.domain = domain.value;
        }
        taskHolder.appendChild(domain);

        let name = document.createElement('input');
        if(task.name === undefined){
            name.placeholder = "Name";
        } else{
            name.value = task.name;
        }
        name.oninput = function nameInput(){
            task.name = name.value;
        }
        taskHolder.appendChild(name);
        
        let firstAction = document.createElement('input');
        if(task.firstAction === undefined){
            firstAction.placeholder = "Action";
        } else{
            firstAction.value = task.firstAction;
        }
        firstAction.oninput = function firstActionInput(){
            task.firstAction = firstAction.value;
        }
        taskHolder.appendChild(firstAction);

        let deadDate = document.createElement('input');
        deadDate.type = "date";
        if(task.deadDate === undefined){
            let today = getToday();
            console.log(today);
            deadDate.value = today;
        } else{
            deadDate.value = task.deadDate;
        }
        deadDate.oninput = function deadDateInput(){
            task.deadDate = deadDate.value;
        }
        taskHolder.appendChild(deadDate);

        let deadTime = document.createElement('input');
        deadTime.type = "time";
        if(task.deadTime === undefined){
            deadTime.value = "10:00"
        } else{
            deadTime.value = task.deadTime;
        }
        deadTime.oninput = function deadTimeInput(){
            task.deadTime = deadTime.value;
        }
        taskHolder.appendChild(deadTime);

        let importance = document.createElement('input');
        importance.type = "range";
        importance.min = "0";
        importance.max = "100";
        if(task.importance === undefined){
            importance.value = "10:00"
        } else{
            importance.value = task.importance;
        }
        importance.oninput = function importanceInput(){
            task.importance = importance.value;
        }
        taskHolder.appendChild(importance);

        let deleteButton = document.createElement('button');
        deleteButton.innerHTML = "-";
        deleteButton.className = "addButton"
        taskHolder.appendChild(deleteButton);
        deleteButton.onclick = function(){
            removeTask(task);
        }
        htmlItems = htmlItems.concat(taskHolder);
        document.body.appendChild(taskHolder);
    }
}

function removeTask(task){
    let index = tasks.indexOf(task);
    if (index > -1) {
        tasks.splice(index, 1);
    } else{
        console.error("couldn't remove task_" + task);
    }
    reDraw();
}

function getToday(){
    var today = new Date();
    var yyyy = today.getFullYear();
    var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    var dd = String(today.getDate()).padStart(2, '0');

    today = yyyy + '-' + mm + '-' + dd;

    return today;
}