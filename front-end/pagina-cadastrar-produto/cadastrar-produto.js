const apiUrl = "https://localhost:8084/produto";
if (apiUrl) {
  console.log("Api funcionando");
} else {
  console.log("Api não funcionando");
}
document.getElementById("getDataBtn").addEventListener("click", function () {
  const produtoId = document.getElementById("produtoId").value;

  if (produtoId.length != 0) {
    fetch(`${apiUrl}/${produtoId}`)
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Produto não encontrado, favor inserir um id válido`);
        }
        return response.json();
      })
      .then((data) => {
        document.getElementById("result").value = JSON.stringify(data, null, 2);
        document.getElementById("nmProdutoDisplay").value = data.nmProduto;
        document.getElementById("vlProdutoDisplay").value = data.vlProduto;
        document.getElementById("qtdEstoqueDisplay").value = data.qtdEstoque;
        document.getElementById("idProdutoDisplay").value = data.id;
      })
      .catch((error) => {
        document.getElementById("result").value = error.message;
      });
  } else if (produtoId.length == 0) {
    fetch(`${apiUrl}`)
      .then((response) => {
        if (!response.ok) {
          throw new Error(
            `Não existe nenhum produto disponível no momento ${response.status}`
          );
        }
        return response.json();
      })
      .then((data) => {
        document.getElementById("result").value = JSON.stringify(data, null, 2);
      })
      .catch((error) => {
        document.getElementById("result").value = error.message;
      });
  }
});

//POST
document.getElementById("sendDataBtn").addEventListener("click", function () {
  const nmProduto = document.getElementById("nmProduto").value;
  const vlProduto = parseFloat(document.getElementById("vlProduto").value);
  const qtdEstoque = parseInt(document.getElementById("qtdEstoque").value, 10);
  if (nmProduto && vlProduto) {
    const payload = {
      nmProduto: nmProduto,
      vlProduto: vlProduto,
      qtdEstoque: qtdEstoque,
    };
    fetch(apiUrl, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(payload),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Erro ao cadastrar produto: ${response.statusText}`);
        }
        return response.json();
      })
      .then((data) => {
        alert("Produto cadastrado com sucesso!");
        console.log("Produto cadastrado:", data);
      });
  } else {
    alert("Por favor, preencha todos os campos obrigatórios.");
  }
});

document.getElementById("updateDataBtn").addEventListener("click", function () {
  const idProduto = document.getElementById("idProdutoDisplayatt").value;
  const nmProduto = document.getElementById("nmProdutoDisplayatt").value;
  const vlProduto = parseFloat(
    document.getElementById("vlProdutoDisplayatt").value
  );
  const qtdEstoque = parseInt(
    document.getElementById("qtdEstoqueDisplay").value,
    10
  );
  if (idProduto && nmProduto && vlProduto && qtdEstoque) {
    const payload = {
      nmProduto: nmProduto,
      vlProduto: vlProduto,
      qtdEstoque: qtdEstoque,
    };

    fetch(`${apiUrl}/${idProduto}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(payload),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`Erro ao atualizar produto: ${response.statusText}`);
        }
        return response.json();
      })
      .then((data) => {
        alert("Produto atualizado com sucesso!");
        console.log("Produto atualizado:", data);
      })
      .then((error) => {
        alert(error.message);
      });
  } else {
    alert("Por favor, preencha todos os campos obrigatórios.");
  }
});

document.getElementById("deleteDataBtn").addEventListener("click", function () {
  const idProduto = document.getElementById("idProdutoDisplaydel").value;
  if (idProduto) {
    if (
      confirm(`Tem certeza que deseja deletar o produto com ID %{produtoId}?`)
    ) {
      fetch(`${apiUrl}/${idProduto}`, {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
        },
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error(`Erro ao deletar produto: ${response.statusText}`);
          }
          alert("Produto deletado com sucesso!");
        })
        .catch((error) => {
          alert(error.message);
        });
    }
  } else {
    alert("Por favor, insira um ID de produto válido para deletar.");
  }
});
