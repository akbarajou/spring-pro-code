package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rewards.RewardNetwork;
import rewards.internal.RewardNetworkImpl;
import rewards.internal.account.AccountRepository;
import rewards.internal.account.JdbcAccountRepository;
import rewards.internal.restaurant.JdbcRestaurantRepository;
import rewards.internal.restaurant.RestaurantRepository;
import rewards.internal.reward.JdbcRewardRepository;
import rewards.internal.reward.RewardRepository;

import javax.sql.DataSource;


/**
 * TODO-00: In this lab, you are going to exercise the following:
 * - Creating Spring configuration class
 * - Defining bean definitions within the configuration class
 * - Specifying the dependency relationships among beans
 * - Injecting dependencies through constructor injection
 * - Creating Spring application context in the test code
 *   (WITHOUT using Spring testContext framework)
 *
 * TODO-01: Make this class a Spring configuration class [COMPLETED]
 * - Use an appropriate annotation.
 *
 * TODO-02: Define four empty @Bean methods, one for the
 *          reward-network and three for the repositories. [COMPLETED]
 * - The names of the beans should be:
 *   - rewardNetwork
 *   - accountRepository
 *   - restaurantRepository
 *   - rewardRepository
 *
 * TODO-03: Inject DataSource through constructor injection [COMPLETED]
 * - Each repository implementation has a DataSource
 *   property to be set, but the DataSource is defined
 *   elsewhere (TestInfrastructureConfig.java), so you
 *   will need to define a constructor for this class
 *   that accepts a DataSource parameter.
 * - As it is the only constructor, @Autowired is optional.
 *
 * TODO-04: Implement each @Bean method to contain the code
 *          needed to instantiate its object and set its
 *          dependencies [COMPLETED]
 * - You can create beans from the following implementation classes
 *   - rewardNetwork bean from RewardNetworkImpl class
 *   - accountRepository bean from JdbcAccountRepository class
 *   - restaurantRepository bean from JdbcRestaurantRepository class
 *   - rewardRepository bean from JdbcRewardRepository class
 * - Note that return type of each bean method should be an interface
 *   not an implementation.
 */

@Configuration
public class RewardsConfig {

	// Set this by adding a constructor.
	private DataSource dataSource;


	public RewardsConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	@Bean
	public RewardNetwork rewardNetwork() {
		return new RewardNetworkImpl(accountRepository(), restaurantRepository(), rewardRepository());
	}

	@Bean
	public AccountRepository accountRepository() {
		JdbcAccountRepository repository = new JdbcAccountRepository();
		repository.setDataSource(dataSource);
		return repository;
	}

	@Bean
	public RestaurantRepository restaurantRepository() {
		JdbcRestaurantRepository repository = new JdbcRestaurantRepository();
		repository.setDataSource(dataSource);
		return repository;
	}

	@Bean
	public RewardRepository rewardRepository() {
		JdbcRewardRepository repository = new JdbcRewardRepository();
		repository.setDataSource(dataSource);
		return repository;
	}

}
