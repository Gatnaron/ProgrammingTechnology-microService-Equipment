// Функция для обновления таблицы с оборудованием
function updateEquipmentTable() {
  var tableBody = document.querySelector("#equipmentTable tbody");
  tableBody.innerHTML = "";

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
          "<button onclick='deleteEquipment(" +
          equipment.id +
          ")'>Delete</button>" +
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

// Обновляем таблицу с оборудованием при загрузке страницы
document.addEventListener("DOMContentLoaded", function () {
  updateEquipmentTable();
});
