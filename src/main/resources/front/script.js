// Функция для обновления таблицы с оборудованием
function updateEquipmentTable() {
  var tableBody = document.querySelector("#equipmentTable tbody");
  tableBody.innerHTML = ""; // Очищаем таблицу перед обновлением

  fetch("http://localhost:8080/all")
    .then((response) => {
      if (!response.ok) {
        throw new Error("Failed to fetch equipment.");
      }
      return response.json();
    })
    .then((equipment) => {
      equipment.forEach((equipment) => {
        var row =
          "<tr>" +
          "<td>" +
          equipment.id +
          "</td>" +
          "<td>" +
          equipment.name +
          "</td>" +
          "<td>" +
          (equipment.status ? "Active" : "Inactive") +
          "</td>" +
          "<td>" +
          equipment.typeId +
          "</td>" +
          "<td>" +
          "<button class='button' onclick='deleteEquipment(" +
          equipment.id +
          ")'>Delete</button>" +
          "<button class='button' onclick='updateEquipment(" +
          equipment.id +
          ")'>Update</button>" +
          "<button class='button' onclick='useEquipment(" +
          equipment.id +
          ")'>Use</button>" +
          "<button class='button' onclick='releaseEquipment(" +
          equipment.id +
          ")'>Release</button>" +
          "</td>" +
          "</tr>";
        tableBody.insertAdjacentHTML("beforeend", row);
      });
    })
    .catch((error) => {
      console.error("Failed to fetch equipment:", error);
    });
}

// Функция для создания нового оборудования
function createEquipment(event) {
  event.preventDefault();
  var form = event.target;
  var data = {
    name: form.name.value,
    status: form.status.checked,
    typeId: form.type.value,
  };

  fetch("http://localhost:8080/create", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Failed to create equipment.");
      }
      form.reset(); // Очищаем форму после успешного создания
      updateEquipmentTable(); // Обновляем таблицу с оборудованием
    })
    .catch((error) => {
      console.error("Failed to create equipment:", error);
    });
}

// Функция для удаления оборудования
function deleteEquipment(id) {
  fetch("http://localhost:8080/delete/" + id, {
    method: "DELETE",
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Failed to delete equipment.");
      }
      updateEquipmentTable(); // Обновляем таблицу с оборудованием
    })
    .catch((error) => {
      console.error("Failed to delete equipment:", error);
    });
}

// Функция для обновления оборудования
function updateEquipment(id) {
  // Получаем изначальные значения оборудования
  fetch("http://localhost:8080/" + id)
    .then((response) => {
      if (!response.ok) {
        throw new Error(
          "Не удалось получить подробную информацию об оборудовании."
        );
      }
      return response.json();
    })
    .then((equipment) => {
      var newName = prompt("Введите новое название:", equipment.name);
      if (newName !== null) {
        var confirmStatus = confirm(
          "Вы хотите изменить статус? Текущий статус: " +
            (equipment.status ? "Active" : "Inactive")
        );
        var newStatus = confirmStatus ? !equipment.status : equipment.status;

        var newType = prompt("Введите новый тип:", equipment.typeId);
        if (newType !== null) {
          fetch("http://localhost:8080/update/" + id, {
            method: "PUT",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify({
              name: newName,
              status: newStatus,
              typeId: newType,
            }),
          })
            .then((response) => {
              if (!response.ok) {
                throw new Error("Не удалось обновить оборудование.");
              }
              updateEquipmentTable();
            })
            .catch((error) => {
              console.error("Не удалось обновить оборудование:", error);
            });
        }
      }
    })
    .catch((error) => {
      console.error(
        "Не удалось получить подробную информацию об оборудовании:",
        error
      );
    });
}

// Функция для использования оборудования
function useEquipment(id) {
  fetch("http://localhost:8080/use/" + id, {
    method: "PUT",
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Failed to use equipment.");
      }
      updateEquipmentTable(); // Обновляем таблицу с оборудованием
    })
    .catch((error) => {
      console.error("Failed to use equipment:", error);
    });
}

// Функция для освобождения оборудования
function releaseEquipment(id) {
  fetch("http://localhost:8080/release/" + id, {
    method: "PUT",
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("Failed to release equipment.");
      }
      updateEquipmentTable(); // Обновляем таблицу с оборудованием
    })
    .catch((error) => {
      console.error("Failed to release equipment:", error);
    });
}


document.addEventListener("DOMContentLoaded", function () {
  updateEquipmentTable();
});

// Функция для сортировки таблицы по ID
function sortTableById() {
  var table = document.querySelector("#equipmentTable");
  var rows = Array.from(table.querySelectorAll("tr"));

  rows.shift();

  // Сортируем строки по ID
  rows.sort((a, b) => {
    var idA = parseInt(a.cells[0].textContent);
    var idB = parseInt(b.cells[0].textContent);
    return idA - idB;
  });

  table.querySelector("tbody").innerHTML = "";

  rows.forEach((row) => {
    table.querySelector("tbody").appendChild(row);
  });
}

// Функция для сортировки таблицы по статусу
function sortTableByStatus() {
  var table = document.querySelector("#equipmentTable");
  var rows = Array.from(table.querySelectorAll("tr"));

  rows.shift();

  // Сортируем строки по статусу
  rows.sort((a, b) => {
    var statusA = a.cells[2].textContent.toLowerCase();
    var statusB = b.cells[2].textContent.toLowerCase();
    return statusA.localeCompare(statusB);
  });

  table.querySelector("tbody").innerHTML = "";

  rows.forEach((row) => {
    table.querySelector("tbody").appendChild(row);
  });
}

// Функция для сортировки таблицы по типу
function sortTableByType() {
  var table = document.querySelector("#equipmentTable");
  var rows = Array.from(table.querySelectorAll("tr"));

  rows.shift();

  // Сортируем строки по типу
  rows.sort((a, b) => {
    var typeA = a.cells[3].textContent.toLowerCase();
    var typeB = b.cells[3].textContent.toLowerCase();
    return typeA.localeCompare(typeB);
  });

  table.querySelector("tbody").innerHTML = "";

  rows.forEach((row) => {
    table.querySelector("tbody").appendChild(row);
  });
}

// Добавляем обработчики событий для кнопок сортировки
document.querySelector("#sortById").addEventListener("click", sortTableById);
document
  .querySelector("#sortByStatus")
  .addEventListener("click", sortTableByStatus);
document
  .querySelector("#sortByType")
  .addEventListener("click", sortTableByType);


  function updateUsageTable() {
    var tableBody = document.querySelector("#usageTable tbody");
    tableBody.innerHTML = "";

    fetch("http://localhost:8080/equipment-usage", {
      method: "GET",
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to fetch equipment usage.");
        }
        return response.json();
      })
      .then((usage) => {
        usage.forEach((usage) => {
          var row =
            "<tr>" +
            "<td>" +
            usage.id +
            "</td>" +
            "<td>" +
            usage.equipmentId +
            "</td>" +
            "<td>" +
            usage.eventId +
            "</td>" +
            "<td>" +
            new Date(usage.startTime).toLocaleString() +
            "</td>" +
            "<td>" +
            new Date(usage.endTime).toLocaleString() +
            "</td>" +
            "<td>" +
            "<button class='button' onclick='deleteUsage(" +
            usage.id +
            ")'>Delete</button>" +
            "</td>" +
            "</tr>";
          tableBody.insertAdjacentHTML("beforeend", row);
        });
      })
      .catch((error) => {
        console.error("Failed to fetch equipment usage:", error);
      });
  }

  function createEquipmentUsage(event) {
    event.preventDefault();
    var form = event.target;
    var data = {
      equipmentId: form.equipmentId.value,
      eventId: form.eventId.value,
      startTime: form.startTime.value,
      endTime: form.endTime.value,
    };

    fetch("http://localhost:8080/equipment-usage/add", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to create equipment usage.");
        }
        form.reset();
        updateUsageTable();
        updateEquipmentTable();
      })
      .catch((error) => {
        console.error("Failed to create equipment usage:", error);
      });
  }

  function deleteUsage(id) {
    fetch("http://localhost:8080/equipment-usage/del/" + id, {
      method: "DELETE",
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Failed to delete equipment usage.");
        }
        updateUsageTable();
        updateEquipmentTable();
      })
      .catch((error) => {
        console.error("Failed to delete equipment usage:", error);
      });
  }

  document
  .querySelector("#showUsageButton")
  .addEventListener("click", updateUsageTable);

  document.addEventListener("DOMContentLoaded", function () {
    updateUsageTable();
  });