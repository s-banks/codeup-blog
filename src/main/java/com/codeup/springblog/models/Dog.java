package com.codeup.springblog.models;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dogs")
public class Dog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(length = 50)
	private String breed;

	@Column(length = 50, nullable = false)
	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dog")
	private List<Toy> toys;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "dogs_vets", joinColumns = {@JoinColumn(name = "dog_id")}, inverseJoinColumns = {@JoinColumn(name = "veterinarian_id")})
	private List<Veterinarian> vets;


	public Dog() {
	}

	public Dog(long id, String breed, String name, List<Toy> toys) {
		this.id = id;
		this.breed = breed;
		this.name = name;
		this.toys = toys;
	}

	public Dog(String breed, String name, List<Toy> toys) {
		this.breed = breed;
		this.name = name;
		this.toys = toys;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Toy> getToys() {
		return toys;
	}

	public void setToys(List<Toy> toys) {
		this.toys = toys;
	}
}
